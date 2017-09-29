package app.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppException extends Exception {

    private static final Logger logger = LoggerFactory.getLogger(AppException.class);

    AppException(String message) {
        super(message);
    }
}
