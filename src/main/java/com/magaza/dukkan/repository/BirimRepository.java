package com.magaza.dukkan.repository;

import com.magaza.dukkan.model.Birim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BirimRepository extends JpaRepository<Birim, Long> {
    boolean existsByAd(String ad);

    @Query(value = "SELECT ad FROM birim", nativeQuery = true)
    List<String> findAllAd();

    Birim findByAd(String ad);

}
