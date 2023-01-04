package com.parcel.cost.service.impl;

import com.parcel.cost.component.CostCalculatorFactory;
import com.parcel.cost.error.CustomException;
import com.parcel.cost.request.CalculateCostRequest;
import com.parcel.cost.response.CostResponse;
import com.parcel.cost.service.ParcelCostService;
import com.parcel.cost.service.VoucherService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

@Service
@Log4j2
public class ParcelCostServiceImpl implements ParcelCostService {
    private CostCalculatorFactory factory;
    private VoucherService voucherService;

    @Autowired
    public void setInjectedBean(CostCalculatorFactory factory, VoucherService voucherService) {
        this.factory = factory;
        this.voucherService = voucherService;
    }

    @Override
    public CostResponse getParcelCost(String voucherCode, CalculateCostRequest request) throws CustomException {
        Float cost = factory.getCostCalculatorService(request).getCost(request);
        Float voucherDiscount = 0f;
        if (voucherCode != null) {
            voucherDiscount = voucherService.getVoucherDiscount(voucherCode);
        }
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        float totalCost = Float.parseFloat(decimalFormat.format(cost - voucherDiscount));
        return new CostResponse(totalCost < 0 ? 0 : totalCost);
    }
}
