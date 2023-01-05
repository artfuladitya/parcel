package com.parcel.cost.component;

import com.parcel.cost.constant.OrdinalConstant;
import com.parcel.cost.request.CalculateCostRequest;
import com.parcel.cost.service.factory.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.HashMap;
import java.util.Map;

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
    @Mock
    private Map<OrdinalConstant, CostCalculatorService> weightMap;
    @Mock
    private Map<OrdinalConstant, CostCalculatorService> volumeMap;

    @SuppressWarnings("deprecation")
    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(factory, "rejectWeightGt", 50);
        ReflectionTestUtils.setField(factory, "heavyWeightGt", 10);
        ReflectionTestUtils.setField(factory, "smallVolumeLt", 1500);
        ReflectionTestUtils.setField(factory, "mediumVolumeLt", 2500);
        weightMap = new HashMap<>();
        weightMap.put(OrdinalConstant.TRUE, reject);
        weightMap.put(OrdinalConstant.PARTIAL_TRUE_1, heavy);
        weightMap.put(OrdinalConstant.PARTIAL_TRUE_2, heavy);
        weightMap.put(OrdinalConstant.FALSE, null);
        volumeMap = new HashMap<>();
        volumeMap.put(OrdinalConstant.TRUE, small);
        volumeMap.put(OrdinalConstant.PARTIAL_TRUE_1, medium);
        volumeMap.put(OrdinalConstant.PARTIAL_TRUE_2, medium);
        volumeMap.put(OrdinalConstant.FALSE, large);
        ReflectionTestUtils.setField(factory, "weightMap", weightMap);
        ReflectionTestUtils.setField(factory, "volumeMap", volumeMap);
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
