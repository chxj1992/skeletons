package app.models;

import app.enums.Role;
import app.enums.Status;
import app.exceptions.SystemException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
@ApiModel
public class User implements Serializable {

    @Id
    @GeneratedValue
    @Column(nullable = false, updatable = false)
    private Integer id;

    @Column(nullable = false)
    @ApiModelProperty(value = "用户名")
    private String username;

    @Column(nullable = false, columnDefinition = "CHAR(60)")
    @JsonIgnore
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "role_user", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private List<Role> roles;

    @Column(name = "status", nullable = false, columnDefinition = "TINYINT(4)")
    @JsonIgnore
    private Integer status;

    @Column(name = "last_login_at", insertable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @ApiModelProperty(value = "最后登录时间")
    private Date lastLoginAt;

    @Column(name = "created_at", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @ApiModelProperty(value = "创建时间")
    private Date createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @ApiModelProperty(value = "修改时间")
    private Date updatedAt;


    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @ApiModelProperty(value = "用户角色", dataType = "noahnote.enums.Role")
    public List<Role> getRoles() {
        return this.roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Status getStatus() throws SystemException {
        return Status.parse(this.status).orElseThrow(
                () -> new SystemException("user status invalid : " + this.status)
        );
    }

    public void setStatus(Status status) {
        this.status = status.getValue();
    }

    public Date getLastLoginAt() {
        return lastLoginAt;
    }


    public void setLastLoginAt(Date lastLoginAt) {
        this.lastLoginAt = lastLoginAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

}
