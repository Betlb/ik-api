package com.magaza.dukkan.service;

import com.magaza.dukkan.model.Birim;
import com.magaza.dukkan.repository.BirimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BirimService {

    @Autowired
    final BirimRepository birimRepository;

    @Autowired
    PersonelService personelService;

    public BirimService(BirimRepository birimRepository) {
        this.birimRepository = birimRepository;
    }

    @Autowired
    public void setPersonelService(PersonelService personelService) {
        this.personelService = personelService;
    }

    public List<String> getAllBirims() {
        return birimRepository.findAllAd(Sort.by(Sort.Order.asc("ad")));
    }

    public Birim addBirim(String ad) {
        if (!birimRepository.existsByAd(ad)) {
            Birim newBirimAd = new Birim(ad);
            return birimRepository.save(newBirimAd);
        }
        return null; // or throw an exception if the birimAd already exists
    }

    public boolean deleteBirimAndPersonels(String birimAd) {
        Birim birim = birimRepository.findByAd(birimAd);
        if (birim!=null) {
            personelService.deletePersonelsWithIzinDetayByBirimAd(birimAd);
            birimRepository.delete(birim);
            return true;
        }
        return false;
    }
}
