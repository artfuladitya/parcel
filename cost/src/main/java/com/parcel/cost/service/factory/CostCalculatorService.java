package com.parcel.cost.service.factory;

import com.parcel.cost.error.CustomException;
import com.parcel.cost.request.CalculateCostRequest;

public interface CostCalculatorService {
    Float getCost(CalculateCostRequest request) throws CustomException;
}
