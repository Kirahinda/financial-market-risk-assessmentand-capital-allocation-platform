package com.ytfa.fmrcap.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import java.util.List;
import java.util.UUID;
import jakarta.persistence.Table;

@Entity
@Table(name = "risk_analyst")
public class RiskAnalyst {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String firstName;

    private String lastName;

    private String email;

    @ManyToOne
    @JoinColumn(name = "trading_desk_id")
    private TradingDesk tradingDesk;

    @ManyToMany
    @JoinTable(name = "risk_analyst_asset_class")
    private List<AssetClass> assetClasses;

    public RiskAnalyst() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public TradingDesk getTradingDesk() {
        return tradingDesk;
    }

    public void setTradingDesk(TradingDesk tradingDesk) {
        this.tradingDesk = tradingDesk;
    }

    public List<AssetClass> getAssetClasses() {
        return assetClasses;
    }

    public void setAssetClasses(List<AssetClass> assetClasses) {
        this.assetClasses = assetClasses;
    }
}
