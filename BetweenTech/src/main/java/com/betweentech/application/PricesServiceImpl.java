package com.betweentech.application;

import com.betweentech.application.exceptions.CustomException;
import com.betweentech.application.projections.PricesProjection;
import com.betweentech.domain.PricesRepository;
import com.betweentech.domain.dto.ApiResponse;
import com.betweentech.domain.dto.PricingRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PricesServiceImpl implements PricesService {
    private final PricesRepository pricesRepository;

    public PricesServiceImpl(PricesRepository pricesRepository) {
        this.pricesRepository = pricesRepository;
    }

    @Override
    public ResponseEntity<ApiResponse<Object>> getPrices(PricingRequest request) {
        List<PricesProjection> prices = pricesRepository.findPricesByBrandIdStartDateProductId(request.getBrandId(), request.getApplicationDate(), request.getProductId());
        // Comprueba si se encontraron precios
        if (prices.isEmpty()) {
            throw new CustomException("No se encontraron precios para la solicitud dada");
        }
        // Si se encontraron precios, crea una respuesta exitosa
        return ResponseEntity.ok(ApiResponse.builder()
                .data(prices)
                .message("Datos obtenidos con éxito")
                .success(true)
                .build());
    }
}