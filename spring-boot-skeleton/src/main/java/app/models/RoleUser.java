package app.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import app.enums.Role;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "role_user")
@ApiModel
public class RoleUser implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @ApiModelProperty(value = "User Role", dataType = "noahnote.enums.Role")
    private Role role;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    public Integer getId() {
        return id;
    }

    public Role getRole() {
        return role;
    }

    public RoleUser setRole(Role role) {
        this.role = role;
        return this;
    }

    public User getUser() {
        return user;
    }

    public RoleUser setUser(User user) {
        this.user = user;
        return this;
    }

}
