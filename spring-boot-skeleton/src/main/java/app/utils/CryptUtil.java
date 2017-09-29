package app.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CryptUtil {

    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
