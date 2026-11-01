package com.ytfa.fmrcap.controller;

import com.ytfa.fmrcap.entity.Investor;
import com.ytfa.fmrcap.service.InvestorService;
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
    public ResponseEntity<?> save(@RequestBody Investor investor) {
        try {
            Investor savedInvestor = investorService.save(investor);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(savedInvestor);

        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest()
                    .body(exception.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Investor>> findAll() {
        return ResponseEntity.ok(investorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id) {
        return investorService.findById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("The investor with that ID does not exist"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(
            @PathVariable UUID id,
            @RequestBody Investor investor) {

        if (!investorService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("The investor with that ID does not exist");
        }

        try {
            Investor updatedInvestor = investorService.update(id, investor);
            return ResponseEntity.ok(updatedInvestor);

        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest()
                    .body(exception.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {

        if (!investorService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("The investor with that ID does not exist");
        }

        investorService.deleteById(id);

        return ResponseEntity.ok("Investor deleted successfully");
    }
}
