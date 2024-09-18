package com.magaza.dukkan.repository;

import com.magaza.dukkan.model.Personel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonelRepository extends JpaRepository<Personel,Long> {
    Personel findUserByTc(String tc);
    Page<Personel> findAll(Pageable pageable);

    Page<Personel> findByAdiStartingWithIgnoreCaseAndSoyadiStartingWithIgnoreCase(Pageable pageable, String adi, String soyadi);

    Page<Personel> findByAdiStartingWithIgnoreCaseAndSoyadiStartingWithIgnoreCaseAndBirimAd(Pageable pageable, String adi, String soyadi, String birimAd);


    Page<Personel> findByBirimAd(String birimAd, Pageable pageable);

    List<Personel> findByBirimAd(String birimAd);
}
