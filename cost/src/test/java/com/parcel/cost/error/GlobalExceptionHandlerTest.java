package com.parcel.cost.error;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.UndeclaredThrowableException;

public class GlobalExceptionHandlerTest {
    @InjectMocks
    GlobalExceptionHandler globalExceptionHandler;

    @SuppressWarnings("deprecation")
    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAppConstants() throws InstantiationException, IllegalAccessException {
        globalExceptionHandler.handleCustomException(new CustomException(ErrorCode.INVALID_FIELD));
        globalExceptionHandler.jsonParseException(new Exception("jsonError"));
        globalExceptionHandler.handleRunTimeException(new Exception("RUNTIME Exception"));
        globalExceptionHandler.undeclaredException(new UndeclaredThrowableException(new Throwable()));
        globalExceptionHandler.wrongId(new IllegalArgumentException(new Throwable()));
        globalExceptionHandler.methodNotSupported(new HttpRequestMethodNotSupportedException("GET"));
        globalExceptionHandler.notReadableException(new HttpMessageNotReadableException("message", new Throwable(), new HttpInputMessage() {
            @Override
            public InputStream getBody() throws IOException {
                return null;
            }

            @Override
            public HttpHeaders getHeaders() {
                return null;
            }
        }));
        globalExceptionHandler.handleValidationException(new MissingServletRequestParameterException("key", "param"));
    }
}
