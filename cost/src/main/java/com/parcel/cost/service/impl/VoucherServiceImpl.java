package com.parcel.cost.service.impl;

import com.parcel.cost.error.CustomException;
import com.parcel.cost.error.ErrorCode;
import com.parcel.cost.response.VoucherItem;
import com.parcel.cost.service.VoucherService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@Log4j2
public class VoucherServiceImpl implements VoucherService {

    WebClient.Builder webCLientBuilder;
    @Value("${voucher.api.url}")
    private String url;
    @Value("${voucher.api.key}")
    private String key;

    @Autowired
    public void setInjectedBean(WebClient.Builder webCLientBuilder) {
        this.webCLientBuilder = webCLientBuilder;
    }

    @Override
    public Float getVoucherDiscount(String voucherCode) throws CustomException {
        Map<String, String> req = new HashMap<>();
        req.put("voucherCode", voucherCode);
        req.put("key", key);
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);
        URI uri = builder.buildAndExpand(req).toUri();
        log.info("Voucher API URI: {}", uri);
        VoucherItem voucherItem = webCLientBuilder.build().get().uri(uri).exchangeToMono(this::handleResponse).block();
        if (voucherItem == null)
            throw new CustomException(ErrorCode.INVALID_VOUCHER);
        log.info("Voucher API Response : {}", voucherItem.toString());
        if (voucherItem.getExpiry().before(new Date()))
            throw new CustomException(ErrorCode.VOUCHER_EXPIRED);
        return voucherItem.getDiscount();
    }

    private Mono<VoucherItem> handleResponse(ClientResponse clientResponse) {
        if (clientResponse.statusCode().isError()) {
            return clientResponse.bodyToMono(VoucherItem.class).flatMap(response -> Mono.error(new RuntimeException(response.getError())));
        }
        return clientResponse.bodyToMono(VoucherItem.class);
    }

}
