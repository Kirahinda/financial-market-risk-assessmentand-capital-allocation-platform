package com.ytfa.fmrcap.service;

import com.ytfa.fmrcap.entity.Investor;
import com.ytfa.fmrcap.entity.RiskAnalyst;
import com.ytfa.fmrcap.entity.RiskAssessment;
import com.ytfa.fmrcap.repository.InvestorRepository;
import com.ytfa.fmrcap.repository.RiskAnalystRepository;
import com.ytfa.fmrcap.repository.RiskAssessmentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RiskAssessmentService {

    private final RiskAssessmentRepository riskAssessmentRepository;
    private final InvestorRepository investorRepository;
    private final RiskAnalystRepository riskAnalystRepository;

    public RiskAssessmentService(
            RiskAssessmentRepository riskAssessmentRepository,
            InvestorRepository investorRepository,
            RiskAnalystRepository riskAnalystRepository) {

        this.riskAssessmentRepository = riskAssessmentRepository;
        this.investorRepository = investorRepository;
        this.riskAnalystRepository = riskAnalystRepository;
    }

    public RiskAssessment save(RiskAssessment assessment) {

        resolveRelationships(assessment);

        return riskAssessmentRepository.save(assessment);
    }

    public List<RiskAssessment> findAll() {
        return riskAssessmentRepository.findAll();
    }

    public Optional<RiskAssessment> findById(UUID id) {
        return riskAssessmentRepository.findById(id);
    }

    public boolean existsById(UUID id) {
        return riskAssessmentRepository.existsById(id);
    }

    public RiskAssessment update(UUID id, RiskAssessment assessment) {

        assessment.setId(id);

        resolveRelationships(assessment);

        return riskAssessmentRepository.save(assessment);
    }

    public void deleteById(UUID id) {
        riskAssessmentRepository.deleteById(id);
    }

    private void resolveRelationships(RiskAssessment assessment) {

        if (assessment.getInvestor() == null
                || assessment.getInvestor().getId() == null) {
            throw new IllegalArgumentException(
                    "A valid investor ID is required");
        }

        UUID investorId = assessment.getInvestor().getId();

        Investor investor = investorRepository.findById(investorId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Investor not found: " + investorId));

        assessment.setInvestor(investor);

        if (assessment.getRiskAnalyst() == null
                || assessment.getRiskAnalyst().getId() == null) {
            throw new IllegalArgumentException(
                    "A valid risk analyst ID is required");
        }

        UUID analystId = assessment.getRiskAnalyst().getId();

        RiskAnalyst riskAnalyst = riskAnalystRepository.findById(analystId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Risk analyst not found: " + analystId));

        assessment.setRiskAnalyst(riskAnalyst);
    }
}
