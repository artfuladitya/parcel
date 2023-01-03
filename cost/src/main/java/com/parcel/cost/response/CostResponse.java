package com.parcel.cost.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CostResponse {
    private Float parcelCost;
    private Float voucherDiscount;
    private Float totalCost;

}
