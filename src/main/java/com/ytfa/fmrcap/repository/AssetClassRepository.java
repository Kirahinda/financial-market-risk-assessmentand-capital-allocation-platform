package com.ytfa.fmrcap.repository;

import com.ytfa.fmrcap.entity.AssetClass;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface AssetClassRepository extends JpaRepository<AssetClass, UUID> {

}
