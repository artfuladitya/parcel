package com.parcel.cost.service.factory;

import com.parcel.cost.request.CalculateCostRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LargeCostCalculatorServiceTest {
    @InjectMocks
    LargeCostCalculatorServiceImpl largeCostCalculatorService;

    @SuppressWarnings("deprecation")
    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(largeCostCalculatorService, "multiplicationFactor", 0.05f);
    }

    @Test
    public void testCalculateCost() {
        assertEquals(130.0f, largeCostCalculatorService.getCost(new CalculateCostRequest(12d, 100d, 2d, 13d)));
    }

}
