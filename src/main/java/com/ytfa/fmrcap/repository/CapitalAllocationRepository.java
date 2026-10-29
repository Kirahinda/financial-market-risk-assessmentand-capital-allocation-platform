package com.ytfa.fmrcap.repository;

import com.ytfa.fmrcap.entity.CapitalAllocation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CapitalAllocationRepository
        extends JpaRepository<CapitalAllocation, UUID> {
}
