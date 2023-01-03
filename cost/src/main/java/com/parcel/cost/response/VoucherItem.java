package com.parcel.cost.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class VoucherItem {

    private String code;
    private Float discount;
    private Date expiry;
    private String error;
}
