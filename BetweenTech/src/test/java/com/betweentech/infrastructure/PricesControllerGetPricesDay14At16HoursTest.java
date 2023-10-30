package com.betweentech.infrastructure;

import com.betweentech.application.PricesService;
import com.betweentech.application.projections.PricesProjection;
import com.betweentech.domain.dto.ApiResponse;
import com.betweentech.domain.dto.PricingRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PricesController.class)
@ComponentScan(basePackages = "com.betweentech.infrastructure.config")
public class PricesControllerGetPricesDay14At16HoursTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PricesService pricesService;

    @Test
    public void testGetPricesForNonExistentData() throws Exception {
        // Fecha,no existen en la base de datos
        String applicationDate = "2020-06-14-16.00.00"; // Una hora que no está en los datos de prueba
        Long productId = 35455L; // Un producto  existe en los datos de prueba
        Long brandId = 1L; // Una marca  existe en los datos de prueba

        // Datos de ejemplo que esperas que el servicio retorne
        List<PricesProjection> mockPrices = new ArrayList<>();
        // En este caso, la lista mockPrices estará vacía porque no se encontraron datos

        // Crea una instancia de ApiResponse con un mensaje de error personalizado
        ApiResponse<Object> apiResponse = ApiResponse.builder()
                .data(mockPrices)
                .success(false)
                .message("Error personalizado: No se encontraron precios para la solicitud dada")
                .build();

        // Crea un ResponseEntity con la instancia de ApiResponse
        ResponseEntity<ApiResponse<Object>> responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);

        // Simula el comportamiento del servicio
        when(pricesService.getPrices(any(PricingRequest.class))).thenReturn(responseEntity);

        // Realiza la solicitud HTTP simulada
        mockMvc.perform(get("/api/prices")
                        .param("applicationDate", applicationDate)
                        .param("productId", productId.toString())
                        .param("brandId", brandId.toString()))
                .andExpect(status().isNotFound()) // Espera una respuesta HTTP con código 404 (NOT FOUND)
                .andExpect(jsonPath("$.success").value(false)) // Verifica que "success" sea false en la respuesta JSON
                .andExpect(jsonPath("$.message").value("Error personalizado: No se encontraron precios para la solicitud dada")); // Verifica el mensaje de error
    }
}