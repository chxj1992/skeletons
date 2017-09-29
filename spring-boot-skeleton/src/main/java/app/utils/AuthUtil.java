package app.utils;

import app.exceptions.AuthenticationException;
import app.models.User;
import app.security.SecurityUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthUtil {

    public static User currentUser() throws AuthenticationException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null && auth.getPrincipal() instanceof SecurityUser) {
            SecurityUser securityUser = (SecurityUser) auth.getPrincipal();
            return securityUser.getUser();
        }

        throw new AuthenticationException("authentication required");
    }

}
