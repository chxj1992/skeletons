package app.exceptions;

import app.responses.Response;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public Response businessErrorHandler(HttpServletRequest req, BusinessException e) throws Exception {
        logger.error("business exception : " + e.getMessage());

        return new Response(e.getMessage());
    }

    @ExceptionHandler(value = SystemException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response systemErrorHandler(HttpServletRequest req, SystemException e) throws Exception {
        logger.error("system exception : " + e.getMessage());
        logger.error("trace info : " + ExceptionUtils.getStackTrace(e));

        return new Response("system error");
    }

    @ExceptionHandler(value = AuthenticationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Response authenticationErrorHandler(HttpServletRequest req, HttpServletResponse res, AuthenticationException e) throws Exception {
        logger.error("authentication exception : " + e.getMessage());
        logger.error("trace info : " + ExceptionUtils.getStackTrace(e));

        return new Response("authentication required");
    }

    @ExceptionHandler(value = NoHandlerFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response _404Handler(HttpServletRequest req, HttpServletResponse res, NoHandlerFoundException e) throws Exception {
        return new Response("no handler found for request " + req.getRequestURI() + " with method " + req.getMethod());
    }

    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response argumentMissMatchExceptionHandler(HttpServletRequest req, HttpServletResponse res, MethodArgumentTypeMismatchException e) throws Exception {
        return new Response("invalid arguments : " + e.getMessage());
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response methodNotSupportExceptionHandler(HttpServletRequest req, HttpServletResponse res, HttpRequestMethodNotSupportedException e) throws Exception {
        return new Response(e.getMessage());
    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response argumentMissingExceptionHandler(HttpServletRequest req, HttpServletResponse res, MissingServletRequestParameterException e) throws Exception {
        return new Response("arguments missing : " + e.getMessage());
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response messageNotReadableExceptionHandler(HttpServletRequest req, HttpServletResponse res, HttpMessageNotReadableException e) throws Exception {
        return new Response(e.getMessage());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response validatorExceptionHandler(HttpServletRequest req, HttpServletResponse res, MethodArgumentNotValidException e) throws Exception {
        return new Response(e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response constraintViolationExceptionHandler(HttpServletRequest req, HttpServletResponse res, ConstraintViolationException e) throws Exception {
        return new Response(e.getConstraintViolations().iterator().next().getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response baseHandler(HttpServletRequest req, HttpServletResponse res, Exception e) throws Exception {
        logger.error("internal system error : " + e.getMessage());
        logger.error("trace info : " + ExceptionUtils.getStackTrace(e));

        return new Response("system error");
    }

}