package com.ytfa.fmrcap.controller;

import com.ytfa.fmrcap.entity.TradingDesk;
import com.ytfa.fmrcap.service.TradingDeskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/trading-desks")
public class TradingDeskController {

    private final TradingDeskService tradingDeskService;

    public TradingDeskController(TradingDeskService tradingDeskService) {
        this.tradingDeskService = tradingDeskService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody TradingDesk tradingDesk) {
        tradingDeskService.save(tradingDesk);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Trading desk saved successfully");
    }

    @GetMapping("/all")
    public ResponseEntity<List<TradingDesk>> findAll() {
        return ResponseEntity.ok(tradingDeskService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id) {
        return tradingDeskService.findById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("The trading desk with that id does not exist"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(
            @PathVariable UUID id,
            @RequestBody TradingDesk tradingDesk) {

        if (!tradingDeskService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("The trading desk with that id does not exist");
        }

        tradingDesk.setId(id);
        tradingDeskService.save(tradingDesk);

        return ResponseEntity.ok("Trading desk updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {

        if (!tradingDeskService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("The trading desk with that id does not exist");
        }

        tradingDeskService.deleteById(id);

        return ResponseEntity.ok("Trading desk deleted successfully");
    }
}
