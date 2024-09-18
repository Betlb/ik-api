package com.magaza.dukkan.repository;

import com.magaza.dukkan.model.Personel;
import com.magaza.dukkan.model.YillikIzinHakkiDetay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface YillikIzinHakkiDetayRepository extends JpaRepository<YillikIzinHakkiDetay,Long> {
    List<YillikIzinHakkiDetay> findByPersonelId(Long id);

}
