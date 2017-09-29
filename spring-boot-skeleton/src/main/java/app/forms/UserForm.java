package app.forms;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

@ApiModel
public class UserForm {

    @NotBlank(message = "{username.NotBlank}", groups = {CREATE.class})
    @ApiModelProperty(value = "用户名")
    private String username;

    @NotBlank(message = "{password.NotBlank}", groups = {CREATE.class})
    @Size(min = 6, message = "{password.Size}", groups = {CREATE.class, UPDATE.class})
    @ApiModelProperty(value = "用户密码")
    private String password;

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

    public interface CREATE {
    }

    public interface UPDATE {
    }
}
