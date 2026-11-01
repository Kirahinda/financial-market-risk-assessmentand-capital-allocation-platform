package com.ytfa.fmrcap.controller;

import com.ytfa.fmrcap.entity.CapitalAllocation;
import com.ytfa.fmrcap.service.CapitalAllocationService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/capital-allocations")
public class CapitalAllocationController {

    private final CapitalAllocationService capitalAllocationService;

    public CapitalAllocationController(
            CapitalAllocationService capitalAllocationService) {
        this.capitalAllocationService = capitalAllocationService;
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(
            @RequestBody CapitalAllocation allocation) {

        try {
            CapitalAllocation savedAllocation =
                    capitalAllocationService.save(allocation);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(savedAllocation);

        } catch (EntityNotFoundException | IllegalArgumentException exception) {
            return ResponseEntity.badRequest()
                    .body(exception.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<CapitalAllocation>> findAll() {
        return ResponseEntity.ok(capitalAllocationService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id) {
        return capitalAllocationService.findById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("The capital allocation with that ID does not exist"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(
            @PathVariable UUID id,
            @RequestBody CapitalAllocation allocation) {

        if (!capitalAllocationService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("The capital allocation with that ID does not exist");
        }

        try {
            CapitalAllocation updatedAllocation =
                    capitalAllocationService.update(id, allocation);

            return ResponseEntity.ok(updatedAllocation);

        } catch (EntityNotFoundException | IllegalArgumentException exception) {
            return ResponseEntity.badRequest()
                    .body(exception.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {

        if (!capitalAllocationService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("The capital allocation with that ID does not exist");
        }

        capitalAllocationService.deleteById(id);

        return ResponseEntity.ok("Capital allocation deleted successfully");
    }
}
