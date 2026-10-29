package com.ytfa.fmrcap.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.UUID;
import jakarta.persistence.Table;

@Entity
@Table(name = "trading_desk")
public class TradingDesk {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private int deskNumber;

    private String name;

    public TradingDesk() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getDeskNumber() {
        return deskNumber;
    }

    public void setDeskNumber(int deskNumber) {
        this.deskNumber = deskNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
