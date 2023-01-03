package com.parcel.cost.service.factory;

import com.parcel.cost.request.CalculateCostRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

@Service("large")
@RefreshScope
public class LargeCostCalculatorServiceImpl implements CostCalculatorService {
    @Value("${parcel.large.multiplication.factor}")
    Float multiplicationFactor;

    @Override
    public Float getCost(CalculateCostRequest request) {
        return (float) (request.getLength() * request.getWidth() * request.getHeight() * multiplicationFactor);
    }
}
