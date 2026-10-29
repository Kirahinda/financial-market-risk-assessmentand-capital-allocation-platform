package com.ytfa.fmrcap.service;

import com.ytfa.fmrcap.entity.AssetClass;
import com.ytfa.fmrcap.repository.AssetClassRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AssetClassService {

    private final AssetClassRepository assetClassRepository;

    public AssetClassService(AssetClassRepository assetClassRepository) {
        this.assetClassRepository = assetClassRepository;
    }

    public AssetClass save(AssetClass assetClass) {
        return assetClassRepository.save(assetClass);
    }

    public List<AssetClass> findAll() {
        return assetClassRepository.findAll();
    }

    public Optional<AssetClass> findById(UUID id) {
        return assetClassRepository.findById(id);
    }

    public boolean existsById(UUID id) {
        return assetClassRepository.existsById(id);
    }

    public void deleteById(UUID id) {
        assetClassRepository.deleteById(id);
    }
}
