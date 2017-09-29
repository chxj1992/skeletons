package app.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SystemException extends AppException {

    private static final Logger logger = LoggerFactory.getLogger(SystemException.class);

    public SystemException(String message) {
        super(message);

        logger.error("system exception : " + message);
    }
}
