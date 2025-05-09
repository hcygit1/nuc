package com.personal.weappadmin.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.Set;

/**
 * 用于处理所有的异常情况
 */
@RestControllerAdvice
public class ExceptionAdvice {

    private static Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public OV handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        logger.error("=============缺少请求参数=============", e);
        return new OV().failure(400, "required_parameter_is_not_present");
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public OV handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        logger.error("=============参数解析失败=============", e);
        return new OV().failure(400, "could_not_read_json");
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public OV handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        logger.error("=============参数验证失败=============", e);
        BindingResult result = e.getBindingResult();
        FieldError error = result.getFieldError();
        String field = error.getField();
        String code = error.getDefaultMessage();
        String message = String.format("%s:%s", field, code);
        return new OV().failure(400, message);
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public OV handleBindException(BindException e) {
        logger.error("=============参数绑定失败=============", e);
        BindingResult result = e.getBindingResult();
        FieldError error = result.getFieldError();
        String field = error.getField();
        String code = error.getDefaultMessage();
        String message = String.format("%s:%s", field, code);
        return new OV().failure(400, message);
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public OV handleServiceException(ConstraintViolationException e) {
        logger.error("=============参数验证失败=============", e);
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        ConstraintViolation<?> violation = violations.iterator().next();
        String message = violation.getMessage();
        return new OV().failure(400, "parameter:" + message);
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public OV handleValidationException(ValidationException e) {
        logger.error("=============参数验证失败=============", e);
        return new OV().failure(400, "validation_exception");
    }

    /**
     * 401 - Unauthorized
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AuthorizedException.class)
    public OV handleTokenException(AuthorizedException e) {
        return new OV().failure(401, e.getMessage());
    }

    /**
     * 405 - Method Not Allowed
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public OV handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        logger.error("=============不支持当前请求方法=============", e);
        return new OV().failure(405, "request_method_not_supported");
    }

    /**
     * 415 - Unsupported Media Type
     */
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public OV handleHttpMediaTypeNotSupportedException(Exception e) {
        logger.error("=============不支持当前媒体类型=============", e);
        return new OV().failure(415, "content_type_not_supported");
    }



    /**
     * 500 - Internal Server
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public OV handleException(Exception e) {
        logger.error("=============通用异常=============", e);
        return new OV().failure(500, e.getMessage());
    }

    /**
     * 402 - 已知服务运行异常
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MyException.class)
    public OV handleServiceException(MyException e) {
        return new OV().failure(402, e.getMessage());
    }
}
