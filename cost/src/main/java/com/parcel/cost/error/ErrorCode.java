package com.parcel.cost.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    INVALID_FIELD("INVALID_FIELDS-", HttpStatus.BAD_REQUEST),
    INVALID_VOUCHER("INVALID_VOUCHER-", HttpStatus.BAD_REQUEST),
    VOUCHER_EXPIRED("VOUCHER_EXPIRED", HttpStatus.BAD_REQUEST),
    VOUCHER_SERVICE_UNAVAILABLE("VOUCHER_SERVICE_UNAVAILABLE-", HttpStatus.SERVICE_UNAVAILABLE),
    PARCEL_REJECTED("PARCEL_REJECTED", HttpStatus.NOT_ACCEPTABLE);

    private final String message;
    private final HttpStatus httpStatus;

}
