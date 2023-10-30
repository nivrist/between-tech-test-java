package com.betweentech.application.exceptions;

import com.betweentech.domain.dto.ApiResponse;
import com.betweentech.infrastructure.config.MessageProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class CustomExceptionHandler {

    private final MessageProvider<String> messageProvider;

    public CustomExceptionHandler(MessageProvider<String> messageProvider) {
        this.messageProvider = messageProvider;
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiResponse<Object>> handleCustomException(CustomException ex) {
        log.error(messageProvider.getMessage("prices.log.error.custom"), ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.builder()
                        .message(messageProvider.getMessage("prices.log.error.msj") + " " + ex.getMessage())
                        .success(false)
                        .build());
    }
}
