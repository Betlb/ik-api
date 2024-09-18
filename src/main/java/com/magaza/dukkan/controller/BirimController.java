package com.magaza.dukkan.controller;

import com.magaza.dukkan.model.Birim;
import com.magaza.dukkan.service.BirimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/birim")
public class BirimController {

    @Autowired
    BirimService birimService;

    @GetMapping("/birims")
    public ResponseEntity<List<String>> getAllBirims() {
        return ResponseEntity.ok(birimService.getAllBirims());
    }

    @PostMapping("/addBirim")
    public ResponseEntity<Birim> addBirim(@RequestParam String birimAd) {
        Birim newBirim = birimService.addBirim(birimAd);
        if (newBirim != null) {
            return ResponseEntity.ok(newBirim);
        }
        return ResponseEntity.badRequest().build(); // Return error if the birimAd already exists
    }

    @DeleteMapping("/deleteBirimByAd")
    public ResponseEntity<Void> deleteBirimByAd(@RequestParam String birimAd) {
        boolean isDeleted = birimService.deleteBirimAndPersonels(birimAd);
        if (isDeleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
