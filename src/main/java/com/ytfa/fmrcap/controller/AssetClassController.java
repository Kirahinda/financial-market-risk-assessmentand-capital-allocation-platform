package com.ytfa.fmrcap.controller;

import com.ytfa.fmrcap.entity.AssetClass;
import com.ytfa.fmrcap.service.AssetClassService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/asset-classes")
public class AssetClassController {

    private final AssetClassService assetClassService;

    public AssetClassController(AssetClassService assetClassService) {
        this.assetClassService = assetClassService;
    }

    @PostMapping("/save")
    public ResponseEntity<AssetClass> save(@RequestBody AssetClass assetClass) {
        AssetClass savedAssetClass = assetClassService.save(assetClass);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAssetClass);
    }

    @GetMapping("/all")
    public ResponseEntity<List<AssetClass>> findAll() {
        return ResponseEntity.ok(assetClassService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id) {
        return assetClassService.findById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("The asset class with that id does not exist"));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(
            @PathVariable UUID id,
            @RequestBody AssetClass assetClass) {

        if (!assetClassService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("The asset class with that id does not exist");
        }

        assetClass.setId(id);
        AssetClass updatedAssetClass = assetClassService.save(assetClass);

        return ResponseEntity.ok(updatedAssetClass);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {

        if (!assetClassService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("The asset class with that id does not exist");
        }

        assetClassService.deleteById(id);

        return ResponseEntity.ok("Asset class deleted successfully");
    }

}
