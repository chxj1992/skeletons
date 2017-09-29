package app.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BusinessException extends AppException {

    private static final Logger logger = LoggerFactory.getLogger(BusinessException.class);

    public BusinessException(String message) {
        super(message);

        logger.error("business exception : " + message);
    }
}
