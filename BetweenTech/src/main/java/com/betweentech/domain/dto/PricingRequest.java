package com.betweentech.domain.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class PricingRequest {
    private Date applicationDate;
    private Long productId;
    private Long brandId;
}
