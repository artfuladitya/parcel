package com.parcel.cost.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.time.Instant;

@Getter
@Setter
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ErrorBody {
    private HttpStatus status;
    private String message;
    private String timestamp;
    private String description;

    public ErrorBody(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = Instant.now().toString();
    }

    public ErrorBody(HttpStatus status, String message, String description) {
        this.status = status;
        this.message = message;
        this.description = description;
        this.timestamp = Instant.now().toString();
    }
}
