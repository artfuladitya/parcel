package com.parcel.cost.service;

import com.parcel.cost.component.CostCalculatorFactory;
import com.parcel.cost.error.CustomException;
import com.parcel.cost.request.CalculateCostRequest;
import com.parcel.cost.response.CostResponse;
import com.parcel.cost.service.factory.CostCalculatorService;
import com.parcel.cost.service.impl.ParcelCostServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class ParcelCostServiceImplTest {
    @InjectMocks
    ParcelCostServiceImpl parcelCostService;
    @Mock
    CostCalculatorService costCalculatorService;
    @Mock
    private CostCalculatorFactory factory;
    @Mock
    private VoucherService voucherService;
    private CostResponse costResponse;

    private CalculateCostRequest request;

    @SuppressWarnings("deprecation")
    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        costResponse = new CostResponse(122f, 22f, 100f);
        request = new CalculateCostRequest();
    }

    @Test
    public void testInjectBean() {
        parcelCostService.setInjectedBean(factory, voucherService);
    }

    @Test
    public void testGetParcelCost() throws CustomException {
        when(voucherService.getVoucherDiscount(anyString()))
                .thenReturn(22f);

        when(factory.getCostCalculatorService(any(CalculateCostRequest.class)))
                .thenReturn(costCalculatorService);

        when(costCalculatorService.getCost(any(CalculateCostRequest.class)))
                .thenReturn(122f);

        CostResponse parcelCost = parcelCostService.getParcelCost("ZXS", request);
        assertEquals(costResponse.getParcelCost(), parcelCost.getParcelCost());
        parcelCost = parcelCostService.getParcelCost(null, request);
        assertEquals(122f, parcelCost.getParcelCost());
    }

}
