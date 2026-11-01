package com.ytfa.fmrcap.controller;

import com.ytfa.fmrcap.entity.RiskAssessment;
import com.ytfa.fmrcap.service.RiskAssessmentService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/risk-assessments")
public class RiskAssessmentController {

    private final RiskAssessmentService riskAssessmentService;

    public RiskAssessmentController(
            RiskAssessmentService riskAssessmentService) {
        this.riskAssessmentService = riskAssessmentService;
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(
            @RequestBody RiskAssessment assessment) {

        try {
            RiskAssessment savedAssessment =
                    riskAssessmentService.save(assessment);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(savedAssessment);

        } catch (EntityNotFoundException | IllegalArgumentException exception) {
            return ResponseEntity.badRequest()
                    .body(exception.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<RiskAssessment>> findAll() {
        return ResponseEntity.ok(riskAssessmentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id) {
        return riskAssessmentService.findById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("The risk assessment with that ID does not exist"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(
            @PathVariable UUID id,
            @RequestBody RiskAssessment assessment) {

        if (!riskAssessmentService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("The risk assessment with that ID does not exist");
        }

        try {
            RiskAssessment updatedAssessment =
                    riskAssessmentService.update(id, assessment);

            return ResponseEntity.ok(updatedAssessment);

        } catch (EntityNotFoundException | IllegalArgumentException exception) {
            return ResponseEntity.badRequest()
                    .body(exception.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {

        if (!riskAssessmentService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("The risk assessment with that ID does not exist");
        }

        riskAssessmentService.deleteById(id);

        return ResponseEntity.ok("Risk assessment deleted successfully");
    }
}
