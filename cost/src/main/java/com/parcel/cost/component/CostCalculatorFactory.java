package com.parcel.cost.component;

import com.parcel.cost.constant.OrdinalConstant;
import com.parcel.cost.request.CalculateCostRequest;
import com.parcel.cost.service.factory.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RefreshScope
@Log4j2
public class CostCalculatorFactory {

    @Value("${parcel.reject.rule.weight.gt}")
    Integer rejectWeightGt;
    @Value("${parcel.heavy.rule.weight.gt}")
    Integer heavyWeightGt;
    @Value("${parcel.small.rule.volume.lt}")
    Integer smallVolumeLt;
    @Value("${parcel.medium.rule.volume.lt}")
    Integer mediumVolumeLt;

    private Map<OrdinalConstant, CostCalculatorService> weightMap;
    private Map<OrdinalConstant, CostCalculatorService> volumeMap;


    @Autowired
    public void setInjectedBean(@Qualifier("heavy") HeavyCostCalculatorServiceImpl heavy, @Qualifier("medium") MediumCostCalculatorServiceImpl medium,
                                @Qualifier("large") LargeCostCalculatorServiceImpl large, @Qualifier("small") SmallCostCalculatorServiceImpl small,
                                @Qualifier("reject") RejectCostCalculatorServiceImpl reject) {
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
    }


    public CostCalculatorService getCostCalculatorService(CalculateCostRequest request) {
        double volume = request.getLength() * request.getWidth() * request.getHeight();
        CostCalculatorService costCalculatorService;
        costCalculatorService = weightMap.get(OrdinalConstant.valueOf(request.getWeight() > rejectWeightGt, request.getWeight() > heavyWeightGt));
        if (costCalculatorService == null)
            costCalculatorService = volumeMap.get(OrdinalConstant.valueOf(smallVolumeLt > volume, mediumVolumeLt > volume));
        log.info(costCalculatorService.toString());
        return costCalculatorService;
    }
}
