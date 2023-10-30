package com.betweentech.application;

import com.betweentech.application.exceptions.CustomException;
import com.betweentech.application.projections.PricesProjection;
import com.betweentech.domain.PricesRepository;
import com.betweentech.domain.dto.ApiResponse;
import com.betweentech.domain.dto.PricingRequest;
import com.betweentech.infrastructure.config.MessageProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PricesServiceImpl implements PricesService {
    private final PricesRepository pricesRepository;
    private final MessageProvider<String> messageProvider;

    public PricesServiceImpl(PricesRepository pricesRepository, MessageProvider<String> messageProvider) {
        this.pricesRepository = pricesRepository;
        this.messageProvider = messageProvider;
    }

    @Override
    public ResponseEntity<ApiResponse<Object>> getPrices(PricingRequest request) {
        List<PricesProjection> prices = pricesRepository.findPricesByBrandIdStartDateProductId(request.getBrandId(), request.getApplicationDate(), request.getProductId());
        // Comprueba si se encontraron precios
        if (prices.isEmpty()) {
            throw new CustomException(messageProvider.getMessage("prices.message.list.empty"));
        }
        // Si se encontraron precios, crea una respuesta exitosa
        return ResponseEntity.ok(ApiResponse.builder()
                .data(prices)
                .message(messageProvider.getMessage("prices.message.list.ok"))
                .success(true)
                .build());
    }
}