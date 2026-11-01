package com.ytfa.fmrcap.service;

import com.ytfa.fmrcap.entity.CapitalAllocation;
import com.ytfa.fmrcap.entity.RiskAssessment;
import com.ytfa.fmrcap.repository.CapitalAllocationRepository;
import com.ytfa.fmrcap.repository.RiskAssessmentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CapitalAllocationService {

    private final CapitalAllocationRepository capitalAllocationRepository;
    private final RiskAssessmentRepository riskAssessmentRepository;

    public CapitalAllocationService(
            CapitalAllocationRepository capitalAllocationRepository,
            RiskAssessmentRepository riskAssessmentRepository) {

        this.capitalAllocationRepository = capitalAllocationRepository;
        this.riskAssessmentRepository = riskAssessmentRepository;
    }

    public CapitalAllocation save(CapitalAllocation allocation) {

        resolveRiskAssessment(allocation);

        return capitalAllocationRepository.save(allocation);
    }

    public List<CapitalAllocation> findAll() {
        return capitalAllocationRepository.findAll();
    }

    public Optional<CapitalAllocation> findById(UUID id) {
        return capitalAllocationRepository.findById(id);
    }

    public boolean existsById(UUID id) {
        return capitalAllocationRepository.existsById(id);
    }

    public CapitalAllocation update(UUID id, CapitalAllocation allocation) {

        allocation.setId(id);

        resolveRiskAssessment(allocation);

        return capitalAllocationRepository.save(allocation);
    }

    public void deleteById(UUID id) {
        capitalAllocationRepository.deleteById(id);
    }

    private void resolveRiskAssessment(CapitalAllocation allocation) {

        if (allocation.getRiskAssessment() == null
                || allocation.getRiskAssessment().getId() == null) {
            throw new IllegalArgumentException(
                    "A valid risk assessment ID is required");
        }

        UUID assessmentId = allocation.getRiskAssessment().getId();

        RiskAssessment assessment = riskAssessmentRepository
                .findById(assessmentId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Risk assessment not found: " + assessmentId));

        allocation.setRiskAssessment(assessment);
    }
}
