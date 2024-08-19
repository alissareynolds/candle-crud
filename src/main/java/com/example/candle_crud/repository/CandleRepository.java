package com.example.candle_crud.repository;

import com.example.candle_crud.models.Candle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CandleRepository extends JpaRepository<Candle, UUID> {
    List<Candle> findByName(String name);
}
