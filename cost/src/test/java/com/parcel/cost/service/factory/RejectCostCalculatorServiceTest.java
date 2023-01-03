package com.parcel.cost.service.factory;

import com.parcel.cost.error.CustomException;
import com.parcel.cost.request.CalculateCostRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RejectCostCalculatorServiceTest {
    @InjectMocks
    RejectCostCalculatorServiceImpl rejectCostCalculatorService;

    @SuppressWarnings("deprecation")
    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCalculateCost() {
        try {
            rejectCostCalculatorService.getCost(new CalculateCostRequest(51d, 3d, 2d, 2d));
            assertEquals(false, true);
        } catch (CustomException e) {
            assertEquals(false, false);
        }
    }

}
