package com.parcel.cost.component;

import com.parcel.cost.request.CalculateCostRequest;
import com.parcel.cost.service.factory.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@RefreshScope
public class CostCalculatorFactory {

    @Value("${parcel.reject.rule.weight.gt}")
    Integer rejectWeightGt;
    @Value("${parcel.heavy.rule.weight.gt}")
    Integer heavyWeightGt;
    @Value("${parcel.small.rule.volume.lt}")
    Integer smallVolumeLt;
    @Value("${parcel.medium.rule.volume.lt}")
    Integer mediumVolumeLt;
    private HeavyCostCalculatorServiceImpl heavy;
    private MediumCostCalculatorServiceImpl medium;
    private LargeCostCalculatorServiceImpl large;
    private SmallCostCalculatorServiceImpl small;
    private RejectCostCalculatorServiceImpl reject;

    @Autowired
    public void setInjectedBean(@Qualifier("heavy") HeavyCostCalculatorServiceImpl heavy, @Qualifier("medium") MediumCostCalculatorServiceImpl medium,
                                @Qualifier("large") LargeCostCalculatorServiceImpl large, @Qualifier("small") SmallCostCalculatorServiceImpl small,
                                @Qualifier("reject") RejectCostCalculatorServiceImpl reject) {
        this.heavy = heavy;
        this.medium = medium;
        this.large = large;
        this.small = small;
        this.reject = reject;
    }


    public CostCalculatorService getCostCalculatorService(CalculateCostRequest request) {
        Double volume = request.getLength() * request.getWidth() * request.getHeight();
        if (request.getWeight() > rejectWeightGt)
            return reject;
        else if (request.getWeight() > heavyWeightGt)
            return heavy;
        else if (volume < smallVolumeLt)
            return small;
        else if (volume < mediumVolumeLt)
            return medium;
        else return large;
    }
}
