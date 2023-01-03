package com.parcel.cost.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CalculateCostRequest {
    @NotNull(message = "weight can not be null")
    @DecimalMin(value = "0.01", message = "weight must be at least 0.01")
    private Double weight;
    @NotNull(message = "height can not be null")
    @DecimalMin(value = "0.01", message = "height must be at least 0.01")
    private Double height;
    @NotNull(message = "width can not be null")
    @DecimalMin(value = "0.01", message = "width must be at least 0.01")
    private Double width;
    @NotNull(message = "length can not be null")
    @DecimalMin(value = "0.01", message = "length must be at least 0.01")
    private Double length;
}
