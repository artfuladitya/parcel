package com.parcel.cost.service;


import com.parcel.cost.error.CustomException;

public interface VoucherService {

    Float getVoucherDiscount(String voucherCode) throws CustomException;
}
