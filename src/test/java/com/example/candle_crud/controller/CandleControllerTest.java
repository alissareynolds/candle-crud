package com.example.candle_crud.controller;

import com.example.candle_crud.exception.CandleNotFoundException;
import com.example.candle_crud.models.Candle;
import com.example.candle_crud.models.CandleState;
import com.example.candle_crud.service.CandleService;
import com.sun.source.tree.ModuleTree;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.example.candle_crud.models.CandleState.*;
import static org.junit.jupiter.api.Assertions.*;

class CandleControllerTest {

    private CandleController candleController;
    private CandleService mockCandleService;

    public final Candle input = new Candle(null, "Winter Candy Apple", "apple, pear, orange", "soy", "cotton", 3, 14, 40, BURNED, false);
    public final Candle input2 = new Candle(null, "Cinnamon Caramel Swirl", "carmel, cinnamon, vanilla", "soy", "wax", 1, 7, 20, EMPTY, true);
    public final Candle recordWithId = new Candle(UUID.randomUUID(), "Winter Candy Apple", "apple, pear, orange", "soy", "cotton", 3, 14, 40, BURNED, false);
    public final Candle recordWithId2 = new Candle(recordWithId.getId(), "Cinnamon Caramel Swirl", "carmel, cinnamon, vanilla", "soy", "wax", 1, 7, 20, EMPTY, true);

    public final UUID id = UUID.fromString("59c47568-fde0-4dd7-9aef-03db6a962810");

    @BeforeEach
    public void setup() {
        mockCandleService = Mockito.mock(CandleService.class);
        candleController = new CandleController(mockCandleService);
    }

    @Test
    public void createCandle_shouldReturnCandleAndCREATEDHttpStatus() {
        Mockito.when(mockCandleService.create(Mockito.any())).thenReturn(recordWithId);
        ResponseEntity<Candle> response = candleController.createCandle(input);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(recordWithId, response.getBody());
    }

    @Test
    public void getAllCandles_shouldReturnListOfCandlesAndOKHttpStatus() {
        List<Candle> candles = new ArrayList<>();
        candles.add(input);
        candles.add(input2);
        Mockito.when(mockCandleService.getAllCandles()).thenReturn(candles);
        ResponseEntity<List<Candle>> response = candleController.getAllCandles();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(candles, response.getBody());
    }

    @Test
    public void getCandleById_shouldReturnCandleAndOKHttpStatus() {
        Mockito.when(mockCandleService.getById(recordWithId.getId())).thenReturn(recordWithId);
        ResponseEntity<Candle> response = candleController.getCandleById(recordWithId.getId());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(recordWithId, response.getBody());
    }

    @Test
    public void getCandleById_shouldReturn404WhenCandleNotFound() {
        Mockito.when(mockCandleService.getById(id)).thenThrow(new CandleNotFoundException("A candle with id: " + id + " was not found."));
        ResponseEntity<Candle> response = candleController.getCandleById(id);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void getCandleByName_shouldReturnListOfCandlesAndOKHttpStatus() {
        Mockito.when(mockCandleService.getByName(recordWithId.getName())).thenReturn(List.of(recordWithId));
        ResponseEntity<List<Candle>> response = candleController.getCandleByName(recordWithId.getName());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(List.of(recordWithId), response.getBody());
    }




}