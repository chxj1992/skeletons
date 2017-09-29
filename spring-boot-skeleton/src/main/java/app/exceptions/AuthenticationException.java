package app.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuthenticationException extends AppException {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationException.class);

    public AuthenticationException(String message) {
        super(message);

        logger.error("authentication exception : " + message);
    }
}
