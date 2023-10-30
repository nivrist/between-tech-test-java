package com.betweentech.infrastructure;

import com.betweentech.application.PricesService;
import com.betweentech.domain.dto.ApiResponse;
import com.betweentech.domain.dto.PricingRequest;
import com.betweentech.infrastructure.config.MessageProvider;
import io.swagger.annotations.Api;
import org.springframework.context.MessageSource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping(value = "/api")
@Api(value = "PricesController")
public class PricesController {

	private final MessageProvider<String> messageProvider;
    private final PricesService pricesService;

    public PricesController(MessageSource messageSource, MessageProvider<String> messageProvider, PricesService pricesService) {
		this.messageProvider = messageProvider;
		this.pricesService = pricesService;
    }

    @GetMapping(value = "/prices",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ApiResponse<Object>> prices(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd-HH.mm.ss") Date applicationDate, @RequestParam Long productId, @RequestParam Long brandId) {

		if (applicationDate == null) {
			return ResponseEntity.badRequest().body(ApiResponse.builder()
					.message(messageProvider.getMessage("prices.message.application_date.validate"))
					.success(false)
					.build());
		}

		if (productId == null) {
			return ResponseEntity.badRequest().body(ApiResponse.builder()
					.message(messageProvider.getMessage("prices.message.productId.validate"))
					.success(false)
					.build());
		}

		if (brandId == null) {
			return ResponseEntity.badRequest().body(ApiResponse.builder()
					.message(messageProvider.getMessage("prices.message.brandId.validate"))
					.success(false)
					.build());
		}

		PricingRequest request = PricingRequest.builder()
				.applicationDate(applicationDate)
				.brandId(brandId)
				.productId(productId)
				.build();

		return pricesService.getPrices(request);
    }
}