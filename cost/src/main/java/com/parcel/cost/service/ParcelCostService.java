package com.parcel.cost.service;

import com.parcel.cost.error.CustomException;
import com.parcel.cost.request.CalculateCostRequest;
import com.parcel.cost.response.CostResponse;

public interface ParcelCostService {
    CostResponse getParcelCost(String voucherCode, CalculateCostRequest request) throws CustomException;
}
