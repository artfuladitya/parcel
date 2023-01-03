package com.parcel.cost.error;

import com.fasterxml.jackson.core.JsonParseException;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@ControllerAdvice
@Log4j2
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorBody> handleRunTimeException(Exception e) {
        log.error("Exception : {}", e.getMessage());
        return new ResponseEntity<>(new ErrorBody(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = JsonParseException.class)
    public ResponseEntity<ErrorBody> jsonParseException(Exception e) {
        log.error("JsonParseException : {}", e.getMessage());
        return new ResponseEntity<>(new ErrorBody(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = UndeclaredThrowableException.class)
    public ResponseEntity<ErrorBody> undeclaredException(UndeclaredThrowableException e) {
        log.error("UndeclaredThrowableException : {}", e.getMessage());
        return new ResponseEntity<>(new ErrorBody(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorBody> notReadableException(HttpMessageNotReadableException e) {
        log.error("HttpMessageNotReadableException : {}", e.getMessage());
        return new ResponseEntity<>(new ErrorBody(HttpStatus.BAD_REQUEST, e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorBody> handleValidationException(MethodArgumentNotValidException e) {
        log.error("MethodArgumentNotValidException : {}", e.getMessage());
        BindingResult result = e.getBindingResult();
        Map<String, String> obj = result.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
        ;
        return new ResponseEntity<>(new ErrorBody(HttpStatus.BAD_REQUEST, obj.toString()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorBody> handleValidationException(MissingServletRequestParameterException e) {
        log.error("MissingServletRequestParameterException : {}", e.getMessage());
        return new ResponseEntity<>(new ErrorBody(HttpStatus.BAD_REQUEST, ErrorCode.INVALID_FIELD.getMessage() + e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<ErrorBody> wrongId(IllegalArgumentException e) {
        String message = "INVALID_ID";
        if (e.getMessage().split(":").length > 1)
            message += e.getMessage().split(":")[1].replace("[", "").replace("]", "");
        log.error("IllegalArgumentException : {}", message);
        return new ResponseEntity<>(new ErrorBody(HttpStatus.BAD_REQUEST, message), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = CustomException.class)
    public ResponseEntity<ErrorBody> handleCustomException(CustomException e) {
        log.error("CustomException : {}", e.getMessage());
        return new ResponseEntity<>(new ErrorBody(e.getError().getHttpStatus(), e.getMessage(), e.getDescription()), e.getError().getHttpStatus());
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorBody> methodNotSupported(HttpRequestMethodNotSupportedException e) {
        log.error("HttpRequestMethodNotSupportedException : {}", e.getMessage());
        return new ResponseEntity<>(new ErrorBody(HttpStatus.BAD_REQUEST, e.getMessage() + ". Supported Methods: " + e.getSupportedHttpMethods()), HttpStatus.BAD_REQUEST);
    }

}
