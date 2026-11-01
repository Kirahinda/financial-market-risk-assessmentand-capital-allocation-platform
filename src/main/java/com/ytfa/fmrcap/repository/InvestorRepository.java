package com.ytfa.fmrcap.repository;

import com.ytfa.fmrcap.entity.Investor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InvestorRepository extends JpaRepository<Investor, UUID> {

    boolean existsByInvestorCode(String investorCode);

    boolean existsByInvestorCodeAndIdNot(String investorCode, UUID id);
}
