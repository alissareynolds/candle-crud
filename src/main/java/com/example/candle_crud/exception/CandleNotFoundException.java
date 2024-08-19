package com.example.candle_crud.exception;

public class CandleNotFoundException extends RuntimeException{
    public CandleNotFoundException(String message) {
        super(message);
    }
}
