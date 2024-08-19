package com.example.candle_crud.controller;

import com.example.candle_crud.exception.CandleNotFoundException;
import com.example.candle_crud.models.Candle;
import com.example.candle_crud.service.CandleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/candles")
public class CandleController {

    private final CandleService candleService;

    public CandleController(CandleService candleService) {
        this.candleService = candleService;
    }

    @PostMapping
    public ResponseEntity<Candle> createCandle(@RequestBody Candle candle) {
        Candle createdCandle = candleService.create(candle);
        return new ResponseEntity<>(createdCandle, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Candle>> getAllCandles() {
        List<Candle> candles = candleService.getAllCandles();
        return new ResponseEntity<>(candles, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Candle> getCandleById(@PathVariable UUID id) {
        Candle candle;
        try {
            candle = candleService.getById(id);
        } catch (CandleNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(candle, HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Candle>> getCandleByName(@PathVariable String name) {
        List<Candle> candles = candleService.getByName(name);
        return new ResponseEntity<>(candles, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Candle> updateCandle(@RequestBody Candle candle, @PathVariable UUID id) {
        Candle newCandle;
        try {
            newCandle = candleService.update(candle, id);
        } catch (CandleNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(newCandle, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Candle> patchCandle(@RequestBody Candle candle, @PathVariable UUID id) {
        Candle newCandle;
        try {
            newCandle = candleService.patch(candle, id);
        } catch (CandleNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(newCandle, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Candle> deleteCandle(@PathVariable UUID id) {
       candleService.delete(id);
       return new ResponseEntity<>(HttpStatus.OK);
    }
}
