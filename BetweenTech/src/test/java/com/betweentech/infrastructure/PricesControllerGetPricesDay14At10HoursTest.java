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
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@RunWith(SpringRunner.class)
@WebMvcTest(PricesController.class)
public class PricesControllerGetPricesDay14At10HoursTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PricesService pricesService;

    @Test
    public void testGetPrices() throws Exception {
        // Fecha, producto y marca para la prueba
        String applicationDate = "2020-06-14-10.00.00";
        Long productId = 35455L;
        Long brandId = 1L;

        // Datos de ejemplo que esperas que el servicio retorne
        List<PricesProjection> mockPrices = new ArrayList<>();
        // Agrega aquí los objetos PricesProjection que esperas que el servicio retorne

        // Crea una instancia de ApiResponse con los datos simulados
        ApiResponse<Object> apiResponse = ApiResponse.builder()
                .data(mockPrices)
                .success(true)
                .message("Datos obtenidos con éxito")
                .build();

        // Crea un ResponseEntity con la instancia de ApiResponse
        ResponseEntity<ApiResponse<Object>> responseEntity = ResponseEntity.ok(apiResponse);

        // Simula el comportamiento del servicio
        when(pricesService.getPrices(any(PricingRequest.class))).thenReturn(responseEntity);

        // Realiza la solicitud HTTP simulada
        mockMvc.perform(get("/api/prices")
                        .param("applicationDate", applicationDate)
                        .param("productId", productId.toString())
                        .param("brandId", brandId.toString()))
                .andExpect(status().isOk()) // Espera una respuesta HTTP exitosa
                .andExpect(jsonPath("$.success").value(true)); // Verifica si el JSON de respuesta tiene "success" como true


    }
}