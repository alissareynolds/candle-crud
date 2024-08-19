package com.example.candle_crud.controller;

import com.example.candle_crud.models.Candle;
import com.example.candle_crud.models.CandleState;
import com.example.candle_crud.service.CandleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CandleControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CandleService mockCandleService;

    private final Candle candle = new Candle(UUID.fromString("59c47568-fde0-4dd7-9aef-03db6a962810"), "Winter Candy Apple", "apple, pear, orange", "soy", "cotton", 3, 14, 40, CandleState.BURNED, false);

    @Test
    public void createCandle() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .post("/api/candles")
                .content(asJsonString(new Candle(null, "Winter Candy Apple", "apple, pear, orange", "soy", "cotton", 3, 14, 40, CandleState.BURNED, false)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Test
    public void getAllCandles() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/api/candles").accept(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk());
    }
}
