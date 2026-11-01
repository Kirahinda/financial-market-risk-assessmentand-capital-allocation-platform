package com.ytfa.fmrcap.controller;

import com.ytfa.fmrcap.entity.RiskAnalyst;
import com.ytfa.fmrcap.service.RiskAnalystService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/risk-analysts")
public class RiskAnalystController {

    private final RiskAnalystService riskAnalystService;

    public RiskAnalystController(RiskAnalystService riskAnalystService) {
        this.riskAnalystService = riskAnalystService;
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody RiskAnalyst riskAnalyst) {
        try {
            RiskAnalyst savedAnalyst = riskAnalystService.save(riskAnalyst);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(savedAnalyst);

        } catch (EntityNotFoundException | IllegalArgumentException exception) {
            return ResponseEntity.badRequest()
                    .body(exception.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<RiskAnalyst>> findAll() {
        return ResponseEntity.ok(riskAnalystService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id) {
        return riskAnalystService.findById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("The risk analyst with that ID does not exist"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(
            @PathVariable UUID id,
            @RequestBody RiskAnalyst riskAnalyst) {

        if (!riskAnalystService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("The risk analyst with that ID does not exist");
        }

        try {
            riskAnalyst.setId(id);
            RiskAnalyst updatedAnalyst = riskAnalystService.save(riskAnalyst);

            return ResponseEntity.ok(updatedAnalyst);

        } catch (EntityNotFoundException | IllegalArgumentException exception) {
            return ResponseEntity.badRequest()
                    .body(exception.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {

        if (!riskAnalystService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("The risk analyst with that ID does not exist");
        }

        riskAnalystService.deleteById(id);

        return ResponseEntity.ok("Risk analyst deleted successfully");
    }
}
