package com.parcel.cost;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CostApplicationTests {
    @InjectMocks
    CostApplication costApplication;

    @Test
    public void testGetWebClientBuilder() {
        costApplication.getWebClientBuilder();
    }

    @Test
    void contextLoads() {
        costApplication.main(new String[]{""});
    }


}
