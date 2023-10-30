package com.betweentech.application;

import com.betweentech.domain.dto.ApiResponse;
import com.betweentech.domain.dto.PricingRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface PricesService {
    public ResponseEntity<ApiResponse<Object>> getPrices(PricingRequest request);
}
