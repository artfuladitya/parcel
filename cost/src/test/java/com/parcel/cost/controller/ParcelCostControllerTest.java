package com.parcel.cost.controller;


import com.parcel.cost.error.CustomException;
import com.parcel.cost.request.CalculateCostRequest;
import com.parcel.cost.response.CostResponse;
import com.parcel.cost.service.impl.ParcelCostServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class ParcelCostControllerTest {
    @InjectMocks
    ParcelCostController controller;
    @Mock
    ParcelCostServiceImpl parcelCostServiceImpl;
    private CalculateCostRequest request;
    private CostResponse response;


    @SuppressWarnings("deprecation")
    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        response = new CostResponse(122f, 22f, 100f);
        request = new CalculateCostRequest(10d, 1d, 12d, 2d);
    }

    @Test
    public void testInjectBean() {
        controller.setInjectedBean(parcelCostServiceImpl);
    }

    @Test
    public void testGetParcelCost() throws CustomException {
        when(parcelCostServiceImpl.getParcelCost(anyString(), any(CalculateCostRequest.class))).thenReturn(response);
        ResponseEntity<CostResponse> res = controller.calculateCost("XYZ", request);
        assertEquals(response.getTotalCost(), res.getBody().getTotalCost());
        res = controller.calculateCost("XYZ", new CalculateCostRequest(0d, 0d, 0d, 0d));
    }
}
