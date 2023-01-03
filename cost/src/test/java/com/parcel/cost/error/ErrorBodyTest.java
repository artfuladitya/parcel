package com.parcel.cost.error;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

public class ErrorBodyTest {
    @InjectMocks
    ErrorBody errorBody;

    @SuppressWarnings("deprecation")
    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAppConstants() {
        @SuppressWarnings("unused")
        ErrorBody errorBody = new ErrorBody(HttpStatus.ACCEPTED, "message");
        errorBody = new ErrorBody(HttpStatus.ACCEPTED, "message", "description");
    }
}
