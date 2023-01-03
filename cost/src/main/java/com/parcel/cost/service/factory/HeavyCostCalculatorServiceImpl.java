package com.parcel.cost.service.factory;

import com.parcel.cost.request.CalculateCostRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

@Service("heavy")
@RefreshScope
public class HeavyCostCalculatorServiceImpl implements CostCalculatorService {
    @Value("${parcel.heavy.multiplication.factor}")
    Float multiplicationFactor;

    @Override
    public Float getCost(CalculateCostRequest request) {
        return (float) (request.getWeight() * multiplicationFactor);
    }
}
