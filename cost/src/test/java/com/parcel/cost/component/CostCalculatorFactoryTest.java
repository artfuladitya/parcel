package com.parcel.cost.component;

import com.parcel.cost.request.CalculateCostRequest;
import com.parcel.cost.service.factory.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CostCalculatorFactoryTest {
    @InjectMocks
    CostCalculatorFactory factory;
    @Mock
    HeavyCostCalculatorServiceImpl heavy;
    @Mock
    MediumCostCalculatorServiceImpl medium;
    @Mock
    LargeCostCalculatorServiceImpl large;
    @Mock
    SmallCostCalculatorServiceImpl small;
    @Mock
    RejectCostCalculatorServiceImpl reject;

    @SuppressWarnings("deprecation")
    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(factory, "rejectWeightGt", 50);
        ReflectionTestUtils.setField(factory, "heavyWeightGt", 10);
        ReflectionTestUtils.setField(factory, "smallVolumeLt", 1500);
        ReflectionTestUtils.setField(factory, "mediumVolumeLt", 2500);

    }

    @Test
    public void testInjectBean() {
        factory.setInjectedBean(heavy, medium, large, small, reject);
    }

    @Test
    public void testFunc() {
        assertEquals(reject, factory.getCostCalculatorService(new CalculateCostRequest(51d, 1d, 1d, 1d)));
        assertEquals(heavy, factory.getCostCalculatorService(new CalculateCostRequest(11d, 1d, 1d, 1d)));
        assertEquals(small, factory.getCostCalculatorService(new CalculateCostRequest(5d, 10d, 10d, 10d)));
        assertEquals(medium, factory.getCostCalculatorService(new CalculateCostRequest(5d, 20d, 10d, 10d)));
        assertEquals(large, factory.getCostCalculatorService(new CalculateCostRequest(5d, 26d, 10d, 10d)));

    }

}
