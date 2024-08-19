package com.example.candle_crud.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity(name = "candles")
@AllArgsConstructor
@NoArgsConstructor
public class Candle {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private String scent;

    private String type;

    private String wickType;

    private Integer numberOfWicks;

    private Integer numberOfOunces;

    private Integer hoursOfBurnTime;

    @Enumerated(EnumType.STRING)
    private CandleState state;

    private Boolean isFavorite;

    public Candle(String name, String scent, String type, String wickType, Integer numberOfWicks, Integer numberOfOunces, Integer hoursOfBurnTime, CandleState state, Boolean isFavorite) {
        this.name = name;
        this.scent = scent;
        this.type = type;
        this.wickType = wickType;
        this.numberOfWicks = numberOfWicks;
        this.numberOfOunces = numberOfOunces;
        this.hoursOfBurnTime = hoursOfBurnTime;
        this.state = state;
        this.isFavorite = isFavorite;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScent() {
        return scent;
    }

    public void setScent(String scent) {
        this.scent = scent;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWickType() {
        return wickType;
    }

    public void setWickType(String wickType) {
        this.wickType = wickType;
    }

    public Integer getNumberOfWicks() {
        return numberOfWicks;
    }

    public void setNumberOfWicks(Integer numberOfWicks) {
        this.numberOfWicks = numberOfWicks;
    }

    public Integer getNumberOfOunces() {
        return numberOfOunces;
    }

    public void setNumberOfOunces(Integer numberOfOunces) {
        this.numberOfOunces = numberOfOunces;
    }

    public Integer getHoursOfBurnTime() {
        return hoursOfBurnTime;
    }

    public void setHoursOfBurnTime(Integer hoursOfBurnTime) {
        this.hoursOfBurnTime = hoursOfBurnTime;
    }

    public CandleState getState() {
        return state;
    }

    public void setState(CandleState state) {
        this.state = state;
    }

    public Boolean getIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(Boolean isFavorite) {
        this.isFavorite = isFavorite;
    }
}
