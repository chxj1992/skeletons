package app.security;

import com.google.common.collect.Lists;
import app.models.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class SecurityUser extends org.springframework.security.core.userdetails.User {

    private User user;

    public SecurityUser(User user) {
        super(user.getUsername(), user.getPassword(),
                Lists.transform(user.getRoles(), role -> {
                            assert role != null;
                            return new SimpleGrantedAuthority(role.toString());
                        }
                )
        );

        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public Integer getId() {
        return user.getId();
    }

}
