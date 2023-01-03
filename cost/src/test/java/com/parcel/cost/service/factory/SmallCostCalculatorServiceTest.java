package com.parcel.cost.service.factory;

import com.parcel.cost.request.CalculateCostRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SmallCostCalculatorServiceTest {
    @InjectMocks
    SmallCostCalculatorServiceImpl smallCostCalculatorService;

    @SuppressWarnings("deprecation")
    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(smallCostCalculatorService, "multiplicationFactor", 0.03f);
    }

    @Test
    public void testCalculateCost() {
        assertEquals(42.0f, smallCostCalculatorService.getCost(new CalculateCostRequest(8d, 100d, 7d, 2d)));
    }
}
