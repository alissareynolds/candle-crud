package com.example.candle_crud.service;

import com.example.candle_crud.exception.CandleNotFoundException;
import com.example.candle_crud.models.Candle;
import com.example.candle_crud.repository.CandleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.example.candle_crud.models.CandleState.*;
import static org.junit.jupiter.api.Assertions.*;

class CandleServiceTest {

    private CandleService candleService;
    private CandleRepository mockCandleRepository;

    public final Candle input = new Candle(null, "Winter Candy Apple", "apple, pear, orange", "soy", "cotton", 3, 14, 40, BURNED, false);
    public final Candle input2 = new Candle(null, "Cinnamon Caramel Swirl", "carmel, cinnamon, vanilla", "soy", "wax", 1, 7, 20, EMPTY, true);
    public final Candle recordWithId = new Candle(UUID.randomUUID(), "Winter Candy Apple", "apple, pear, orange", "soy", "cotton", 3, 14, 40, BURNED, false);
    public final Candle recordWithId2 = new Candle(recordWithId.getId(), "Cinnamon Caramel Swirl", "carmel, cinnamon, vanilla", "soy", "wax", 1, 7, 20, EMPTY, true);

    public final UUID id = UUID.fromString("59c47568-fde0-4dd7-9aef-03db6a962810");

    @BeforeEach
    public void setup() {
        mockCandleRepository = Mockito.mock(CandleRepository.class);
        candleService = new CandleService(mockCandleRepository);
    }

    @Test
    public void create_shouldReturnCreatedCandle() {
        Mockito.when(mockCandleRepository.save(Mockito.any())).thenReturn(recordWithId);
        Candle response = candleService.create(input);
        assertEquals(recordWithId, response);
    }

    @Test
    public void getAllCandles_shouldReturnListOfCandles() {
        List<Candle> candles = new ArrayList<>();
        candles.add(input);
        candles.add(input2);
        Mockito.when(mockCandleRepository.findAll()).thenReturn(candles);
        List<Candle> response = candleService.getAllCandles();
        assertEquals(candles, response);
    }

    @Test
    public void getById_shouldReturnCandle() {
        Mockito.when(mockCandleRepository.findById(recordWithId.getId())).thenReturn(Optional.of(recordWithId));
        Candle response = candleService.getById(recordWithId.getId());
        assertEquals(recordWithId, response);
    }

    @Test
    public void getById_throwsExceptionWhenBookWasNotFound() {
        Mockito.when(mockCandleRepository.findById(id)).thenReturn(Optional.empty());
        CandleNotFoundException exception = assertThrows(CandleNotFoundException.class, () -> candleService.getById(id));
        assertEquals("A candle with id: " + id + " was not found.", exception.getMessage());
    }

    @Test
    public void getByName_shouldReturnListOfCandles() {
        Mockito.when(mockCandleRepository.findByName(recordWithId.getName())).thenReturn(List.of(recordWithId));
        List<Candle> response = candleService.getByName(recordWithId.getName());
        assertEquals(List.of(recordWithId), response);
    }

    @Test
    public void update_shouldReturnUpdatedCandle() {
        Mockito.when(mockCandleRepository.findById(recordWithId.getId())).thenReturn(Optional.of(recordWithId));
        Mockito.when(mockCandleRepository.save(Mockito.any())).thenReturn(recordWithId);
        Candle response = candleService.update(input2, recordWithId.getId());
        assertEquals(recordWithId, response);
    }

    @Test
    public void update_throwsExceptionWhenCandleWasNotFound() {
        Mockito.when(mockCandleRepository.findById(id)).thenReturn(Optional.empty());
        CandleNotFoundException exception = assertThrows(CandleNotFoundException.class, () -> candleService.update(input, id));
        assertEquals("A candle with id: " + id + " was not found.", exception.getMessage());
    }

    @Test
    public void patch_throwsExceptionWhenCandleWasNotFound() {
        Mockito.when(mockCandleRepository.findById(id)).thenReturn(Optional.empty());
        CandleNotFoundException exception = assertThrows(CandleNotFoundException.class, () -> candleService.patch(input, id));
        assertEquals("A candle with id: " + id + " was not found.", exception.getMessage());
    }

    @Test
    public void patch_shouldReturnUpdatedName() {
        Candle input = new Candle();
        input.setName("Apple");
        Mockito.when(mockCandleRepository.findById(recordWithId.getId())).thenReturn(Optional.of(recordWithId));
        Mockito.when(mockCandleRepository.save(Mockito.any())).thenAnswer(i -> i.getArguments()[0]);
        Candle response = candleService.patch(input, recordWithId.getId());
        assertEquals("Apple", response.getName());
        assertEquals("apple, pear, orange", response.getScent());
        assertEquals("soy", response.getType());
        assertEquals("cotton", response.getWickType());
        assertEquals(3, response.getNumberOfWicks());
        assertEquals(14, response.getNumberOfOunces());
        assertEquals(40, response.getHoursOfBurnTime());
        assertEquals(BURNED, response.getState());
        assertEquals(false, response.getIsFavorite());
    }

    @Test
    public void patch_shouldReturnUpdatedScent() {
        Candle input = new Candle();
        input.setScent("apple");
        Mockito.when(mockCandleRepository.findById(recordWithId.getId())).thenReturn(Optional.of(recordWithId));
        Mockito.when(mockCandleRepository.save(Mockito.any())).thenAnswer(i -> i.getArguments()[0]);
        Candle response = candleService.patch(input, recordWithId.getId());
        assertEquals("Winter Candy Apple", response.getName());
        assertEquals("apple", response.getScent());
        assertEquals("soy", response.getType());
        assertEquals("cotton", response.getWickType());
        assertEquals(3, response.getNumberOfWicks());
        assertEquals(14, response.getNumberOfOunces());
        assertEquals(40, response.getHoursOfBurnTime());
        assertEquals(BURNED, response.getState());
        assertEquals(false, response.getIsFavorite());
    }


    @Test
    public void patch_shouldReturnUpdatedType() {
        Candle input = new Candle();
        input.setType("wax");
        Mockito.when(mockCandleRepository.findById(recordWithId.getId())).thenReturn(Optional.of(recordWithId));
        Mockito.when(mockCandleRepository.save(Mockito.any())).thenAnswer(i -> i.getArguments()[0]);
        Candle response = candleService.patch(input, recordWithId.getId());
        assertEquals("Winter Candy Apple", response.getName());
        assertEquals("apple, pear, orange", response.getScent());
        assertEquals("wax", response.getType());
        assertEquals("cotton", response.getWickType());
        assertEquals(3, response.getNumberOfWicks());
        assertEquals(14, response.getNumberOfOunces());
        assertEquals(40, response.getHoursOfBurnTime());
        assertEquals(BURNED, response.getState());
        assertEquals(false, response.getIsFavorite());
    }

    @Test
    public void patch_shouldReturnUpdatedWickType() {
        Candle input = new Candle();
        input.setWickType("wood");
        Mockito.when(mockCandleRepository.findById(recordWithId.getId())).thenReturn(Optional.of(recordWithId));
        Mockito.when(mockCandleRepository.save(Mockito.any())).thenAnswer(i -> i.getArguments()[0]);
        Candle response = candleService.patch(input, recordWithId.getId());
        assertEquals("Winter Candy Apple", response.getName());
        assertEquals("apple, pear, orange", response.getScent());
        assertEquals("soy", response.getType());
        assertEquals("wood", response.getWickType());
        assertEquals(3, response.getNumberOfWicks());
        assertEquals(14, response.getNumberOfOunces());
        assertEquals(40, response.getHoursOfBurnTime());
        assertEquals(BURNED, response.getState());
        assertEquals(false, response.getIsFavorite());
    }

    @Test
    public void patch_shouldReturnUpdatedNumberOfWicks() {
        Candle input = new Candle();
        input.setNumberOfWicks(1);
        Mockito.when(mockCandleRepository.findById(recordWithId.getId())).thenReturn(Optional.of(recordWithId));
        Mockito.when(mockCandleRepository.save(Mockito.any())).thenAnswer(i -> i.getArguments()[0]);
        Candle response = candleService.patch(input, recordWithId.getId());
        assertEquals("Winter Candy Apple", response.getName());
        assertEquals("apple, pear, orange", response.getScent());
        assertEquals("soy", response.getType());
        assertEquals("cotton", response.getWickType());
        assertEquals(1, response.getNumberOfWicks());
        assertEquals(14, response.getNumberOfOunces());
        assertEquals(40, response.getHoursOfBurnTime());
        assertEquals(BURNED, response.getState());
        assertEquals(false, response.getIsFavorite());
    }

    @Test
    public void patch_shouldReturnUpdatedNumberOfOunces() {
        Candle input = new Candle();
        input.setNumberOfOunces(16);
        Mockito.when(mockCandleRepository.findById(recordWithId.getId())).thenReturn(Optional.of(recordWithId));
        Mockito.when(mockCandleRepository.save(Mockito.any())).thenAnswer(i -> i.getArguments()[0]);
        Candle response = candleService.patch(input, recordWithId.getId());
        assertEquals("Winter Candy Apple", response.getName());
        assertEquals("apple, pear, orange", response.getScent());
        assertEquals("soy", response.getType());
        assertEquals("cotton", response.getWickType());
        assertEquals(3, response.getNumberOfWicks());
        assertEquals(16, response.getNumberOfOunces());
        assertEquals(40, response.getHoursOfBurnTime());
        assertEquals(BURNED, response.getState());
        assertEquals(false, response.getIsFavorite());
    }

    @Test
    public void patch_shouldReturnUpdatedHoursOfBurnTime() {
        Candle input = new Candle();
        input.setHoursOfBurnTime(45);
        Mockito.when(mockCandleRepository.findById(recordWithId.getId())).thenReturn(Optional.of(recordWithId));
        Mockito.when(mockCandleRepository.save(Mockito.any())).thenAnswer(i -> i.getArguments()[0]);
        Candle response = candleService.patch(input, recordWithId.getId());
        assertEquals("Winter Candy Apple", response.getName());
        assertEquals("apple, pear, orange", response.getScent());
        assertEquals("soy", response.getType());
        assertEquals("cotton", response.getWickType());
        assertEquals(3, response.getNumberOfWicks());
        assertEquals(14, response.getNumberOfOunces());
        assertEquals(45, response.getHoursOfBurnTime());
        assertEquals(BURNED, response.getState());
        assertEquals(false, response.getIsFavorite());
    }

    @Test
    public void patch_shouldReturnUpdatedState() {
        Candle input = new Candle();
        input.setState(NEW);
        Mockito.when(mockCandleRepository.findById(recordWithId.getId())).thenReturn(Optional.of(recordWithId));
        Mockito.when(mockCandleRepository.save(Mockito.any())).thenAnswer(i -> i.getArguments()[0]);
        Candle response = candleService.patch(input, recordWithId.getId());
        assertEquals("Winter Candy Apple", response.getName());
        assertEquals("apple, pear, orange", response.getScent());
        assertEquals("soy", response.getType());
        assertEquals("cotton", response.getWickType());
        assertEquals(3, response.getNumberOfWicks());
        assertEquals(14, response.getNumberOfOunces());
        assertEquals(40, response.getHoursOfBurnTime());
        assertEquals(NEW, response.getState());
        assertEquals(false, response.getIsFavorite());
    }
    @Test
    public void patch_shouldReturnUpdatedIsFavorite() {
        Candle input = new Candle();
        input.setIsFavorite(true);
        Mockito.when(mockCandleRepository.findById(recordWithId.getId())).thenReturn(Optional.of(recordWithId));
        Mockito.when(mockCandleRepository.save(Mockito.any())).thenAnswer(i -> i.getArguments()[0]);
        Candle response = candleService.patch(input, recordWithId.getId());
        assertEquals("Winter Candy Apple", response.getName());
        assertEquals("apple, pear, orange", response.getScent());
        assertEquals("soy", response.getType());
        assertEquals("cotton", response.getWickType());
        assertEquals(3, response.getNumberOfWicks());
        assertEquals(14, response.getNumberOfOunces());
        assertEquals(40, response.getHoursOfBurnTime());
        assertEquals(BURNED, response.getState());
        assertEquals(true, response.getIsFavorite());
    }

    @Test
    public void delete_callsRepositoryDeleteMethod() {
        candleService.delete(id);
        Mockito.verify(mockCandleRepository).deleteById(id);
    }


}