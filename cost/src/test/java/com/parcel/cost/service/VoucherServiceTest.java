package com.parcel.cost.service;

import com.parcel.cost.error.CustomException;
import com.parcel.cost.response.VoucherItem;
import com.parcel.cost.service.impl.VoucherServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.function.Consumer;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class VoucherServiceTest {
    @InjectMocks
    VoucherServiceImpl voucherService;

    @Mock
    WebClient.Builder webClientBuilder;

    @Mock
    WebClient webClient;

    @SuppressWarnings("rawtypes")
    @Mock
    WebClient.RequestHeadersUriSpec requestHeadersUriSpec;

    @SuppressWarnings("rawtypes")
    @Mock
    WebClient.RequestHeadersSpec requestHeadersSpec;

    @Mock
    WebClient.RequestBodyUriSpec requestBodyUriSpec;

    @Mock
    WebClient.RequestBodySpec requestBodySpec;

    @Mock
    WebClient.ResponseSpec responseSpec;

    @Mock
    Mono<VoucherItem> voucherItem;
    @Captor
    ArgumentCaptor<Consumer<Object>> objCap;
    private VoucherItem voucherItemResponse;

    @SuppressWarnings("deprecation")
    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(voucherService, "url", "http://localhost:8080");
        voucherItemResponse = new VoucherItem();
        voucherItemResponse.setDiscount(7.5f);
        LocalDate localDate = LocalDate.of(2024, 01, 01);
        voucherItemResponse.setExpiry(Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testGetVoucherDiscount() throws CustomException {
        when(webClientBuilder.build())
                .thenReturn(webClient);
        when(webClient.get())
                .thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(Mockito.any(URI.class)))
                .thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.exchangeToMono(Mockito.any(Function.class)))
                .thenReturn(voucherItem);
        when(voucherItem.block()).
                thenReturn(voucherItemResponse);
        Float voucherDiscount = voucherService.getVoucherDiscount("GFI");
        assertEquals(7.5f, voucherDiscount);
        LocalDate localDate = LocalDate.of(2019, 01, 01);
        voucherItemResponse.setExpiry(Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
        try {
            voucherDiscount = voucherService.getVoucherDiscount("GFI");
        } catch (CustomException e) {
        }
    }

}
