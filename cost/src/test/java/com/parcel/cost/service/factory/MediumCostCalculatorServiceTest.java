package com.parcel.cost.service.factory;

import com.parcel.cost.request.CalculateCostRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MediumCostCalculatorServiceTest {
    @InjectMocks
    MediumCostCalculatorServiceImpl mediumCostCalculatorService;

    @SuppressWarnings("deprecation")
    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(mediumCostCalculatorService, "multiplicationFactor", 0.04f);
    }

    @Test
    public void testCalculateCost() {
        assertEquals(80.0f, mediumCostCalculatorService.getCost(new CalculateCostRequest(12d, 100d, 2d, 10d)));
    }
}
