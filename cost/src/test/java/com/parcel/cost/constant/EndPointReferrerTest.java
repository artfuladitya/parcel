package com.parcel.cost.constant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

public class EndPointReferrerTest {
    @InjectMocks
    EndPointReferrer endPointReferrer;

    @SuppressWarnings("deprecation")
    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCalculateCost() {

        String api = EndPointReferrer.PARCEL + EndPointReferrer.COST;
    }
}
