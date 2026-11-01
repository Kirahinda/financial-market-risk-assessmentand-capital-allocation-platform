package com.ytfa.fmrcap.controller;

import com.ytfa.fmrcap.entity.Investor;
import com.ytfa.fmrcap.service.InvestorService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/investors")
public class InvestorController {

    private final InvestorService investorService;

    public InvestorController(InvestorService investorService) {
        this.investorService = investorService;
    }

    @PostMapping("/save")
    public ResponseEntity<Investor> save(
            @Valid @RequestBody Investor investor) {

        Investor savedInvestor = investorService.save(investor);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedInvestor);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Investor>> findAll() {
        return ResponseEntity.ok(investorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Investor> findById(@PathVariable UUID id) {

        Investor investor = investorService.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Investor not found: " + id));

        return ResponseEntity.ok(investor);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Investor> update(
            @PathVariable UUID id,
            @Valid @RequestBody Investor investor) {

        if (!investorService.existsById(id)) {
            throw new EntityNotFoundException(
                    "Investor not found: " + id);
        }

        Investor updatedInvestor =
                investorService.update(id, investor);

        return ResponseEntity.ok(updatedInvestor);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {

        if (!investorService.existsById(id)) {
            throw new EntityNotFoundException(
                    "Investor not found: " + id);
        }

        investorService.deleteById(id);

        return ResponseEntity.ok("Investor deleted successfully");
    }
}
