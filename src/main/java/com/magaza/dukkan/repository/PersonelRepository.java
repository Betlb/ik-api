package com.magaza.dukkan.repository;

import com.magaza.dukkan.model.Personel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonelRepository extends JpaRepository<Personel,Long> {
    Personel findUserByTc(String tc);
    Page<Personel> findAll(Pageable pageable);

    List<Personel> findByAdSoyadContainingIgnoreCase(String adSoyad);
}
