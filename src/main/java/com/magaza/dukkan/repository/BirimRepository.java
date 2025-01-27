package com.magaza.dukkan.repository;

import com.magaza.dukkan.model.Birim;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BirimRepository extends JpaRepository<Birim, Long> {
    boolean existsByAd(String ad);

    @Query(value = "SELECT b.ad FROM Birim b")
    List<String> findAllAd(Sort sort);

    Birim findByAd(String ad);

}
