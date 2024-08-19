package com.example.candle_crud.service;

import com.example.candle_crud.models.Candle;
import com.example.candle_crud.repository.CandleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.example.candle_crud.models.CandleState.BURNED;
import static com.example.candle_crud.models.CandleState.EMPTY;
import static org.junit.jupiter.api.Assertions.*;

class CandleServiceTest {

    private CandleService mockCandleService;
    private CandleRepository mockCandleRepository;

    public final Candle input = new Candle(null, "Winter Candy Apple", "apple, pear, orange", "soy", "cotton", 3, 14, 40, BURNED, false);
    public final Candle input2 = new Candle(null, "Cinnamon Caramel Swirl", "carmel, cinnamon, vanilla", "soy", "wax", 1, 7, 20, EMPTY, true);
    public final Candle recordWithId = new Candle(UUID.randomUUID(), "Winter Candy Apple", "apple, pear, orange", "soy", "cotton", 3, 14, 40, BURNED, false);
    public final Candle recordWithId2 = new Candle(recordWithId.getId(), "Cinnamon Caramel Swirl", "carmel, cinnamon, vanilla", "soy", "wax", 1, 7, 20, EMPTY, true);

    @BeforeEach
    public void setup() {
        mockCandleRepository = Mockito.mock(CandleRepository.class);
        mockCandleService = new CandleService(mockCandleRepository);
    }



}