package com.parcel.cost.service.factory;

import com.parcel.cost.error.CustomException;
import com.parcel.cost.error.ErrorCode;
import com.parcel.cost.request.CalculateCostRequest;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

@Service("reject")
@RefreshScope
public class RejectCostCalculatorServiceImpl implements CostCalculatorService {

    @Override
    public Float getCost(CalculateCostRequest request) throws CustomException {
        throw new CustomException(ErrorCode.PARCEL_REJECTED);
    }
}
