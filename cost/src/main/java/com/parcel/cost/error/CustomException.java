package com.parcel.cost.error;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomException extends Exception {
    private ErrorCode error;
    private String description;


    public CustomException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.setError(errorCode);
    }
}
