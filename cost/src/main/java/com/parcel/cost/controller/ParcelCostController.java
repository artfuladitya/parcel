package com.parcel.cost.controller;


import com.parcel.cost.constant.EndPointReferrer;
import com.parcel.cost.error.CustomException;
import com.parcel.cost.request.CalculateCostRequest;
import com.parcel.cost.response.CostResponse;
import com.parcel.cost.service.ParcelCostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(EndPointReferrer.PARCEL)
@Tag(name = "Parcel APIs")
@Log4j2
public class ParcelCostController {
    private ParcelCostService service;

    @Autowired
    public void setInjectedBean(ParcelCostService service) {
        this.service = service;
    }


    @PostMapping(EndPointReferrer.COST)
    @Operation(summary = "Calculate Cost Of Delivery", description = "Calculate the cost of delivery of a parcel based on weight")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CostResponse.class))})
    })
    public ResponseEntity<CostResponse> calculateCost(@Parameter(description = "Voucher Code to apply discount") @RequestParam(required = false) String voucher,
                                                      @Valid @RequestBody CalculateCostRequest request) throws CustomException {
        log.info(EndPointReferrer.PARCEL + EndPointReferrer.COST + " API request -Parameters received : Voucher=" + voucher + " ,Request=" + request.toString());
        CostResponse response = service.getParcelCost(voucher, request);
        log.info(EndPointReferrer.PARCEL + EndPointReferrer.COST + " API response -{}", response.toString());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
