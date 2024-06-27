package com.magaza.dukkan.repository;

import com.magaza.dukkan.model.Urun;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UrunRepository extends JpaRepository<Urun,Long> {

    List<Urun> findAllByUrunAdiContainingIgnoreCase(String urunAdi);

}
