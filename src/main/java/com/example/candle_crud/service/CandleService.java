package com.example.candle_crud.service;

import com.example.candle_crud.exception.CandleNotFoundException;
import com.example.candle_crud.models.Candle;
import com.example.candle_crud.repository.CandleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CandleService {

    private final CandleRepository candleRepository;

    public CandleService(CandleRepository candleRepository) {
        this.candleRepository = candleRepository;
    }

    public Candle create(Candle candle) {
        return candleRepository.save(candle);
    }

    public List<Candle> getAllCandles() {
        return candleRepository.findAll();
    }

    public Candle getById(UUID id) {
        Optional<Candle> candleOptional = candleRepository.findById(id);
        if (candleOptional.isEmpty()) {
            throw new CandleNotFoundException("A candle with id: " + id + " was not found.");
        }
        return candleOptional.get();
    }

    public List<Candle> getByName(String name) {
        return candleRepository.findByName(name);
    }

    public Candle update(Candle candle, UUID id) {
        Optional<Candle> originalCandle = candleRepository.findById(id);
        if (originalCandle.isEmpty()) {
            throw new CandleNotFoundException("A candle with id: " + id + " was not found.");
        }
        Candle updatedCandle = new Candle(id, candle.getName(), candle.getScent(), candle.getType(), candle.getWickType(), candle.getNumberOfWicks(), candle.getNumberOfOunces(), candle.getHoursOfBurnTime(), candle.getState(), candle.getIsFavorite());
        return candleRepository.save(updatedCandle);
    }

    public Candle patch(Candle candle, UUID id) {
        Optional<Candle> originalCandle = candleRepository.findById(id);
        if (originalCandle.isEmpty()) {
            throw new CandleNotFoundException("A candle with id: " + id + " was not found.");
        }
        Candle updatedCandle = originalCandle.get();
        if (candle.getName() != null) {
            updatedCandle.setName(candle.getName());
        }
        if (candle.getScent() != null) {
            updatedCandle.setScent(candle.getScent());
        }
        if (candle.getType() != null) {
            updatedCandle.setType(candle.getType());
        }
        if (candle.getWickType() != null) {
            updatedCandle.setWickType(candle.getWickType());
        }
        if (candle.getNumberOfWicks() != null) {
            updatedCandle.setNumberOfWicks(candle.getNumberOfWicks());
        }
        if (candle.getNumberOfOunces() != null) {
            updatedCandle.setNumberOfOunces(candle.getNumberOfOunces());
        }
        if (candle.getHoursOfBurnTime() != null) {
            updatedCandle.setHoursOfBurnTime(candle.getHoursOfBurnTime());
        }
        if (candle.getState() != null) {
            updatedCandle.setState(candle.getState());
        }
        if (candle.getIsFavorite() != null) {
            updatedCandle.setIsFavorite(candle.getIsFavorite());
        }
        return candleRepository.save(updatedCandle);
    }

    public void delete(UUID id) {
        candleRepository.deleteById(id);
    }
}
