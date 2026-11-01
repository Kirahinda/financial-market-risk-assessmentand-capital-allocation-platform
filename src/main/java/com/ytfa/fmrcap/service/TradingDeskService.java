package com.ytfa.fmrcap.service;

import com.ytfa.fmrcap.entity.TradingDesk;
import com.ytfa.fmrcap.repository.TradingDeskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TradingDeskService {

    private final TradingDeskRepository tradingDeskRepository;

    public TradingDeskService(TradingDeskRepository tradingDeskRepository) {
        this.tradingDeskRepository = tradingDeskRepository;
    }

    public TradingDesk save(TradingDesk tradingDesk) {
        return tradingDeskRepository.save(tradingDesk);
    }

    public List<TradingDesk> findAll() {
        return tradingDeskRepository.findAll();
    }

    public Optional<TradingDesk> findById(UUID id) {
        return tradingDeskRepository.findById(id);
    }

    public boolean existsById(UUID id) {
        return tradingDeskRepository.existsById(id);
    }

    public void deleteById(UUID id) {
        tradingDeskRepository.deleteById(id);
    }
}
