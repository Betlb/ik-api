package com.magaza.dukkan.repository;

import com.magaza.dukkan.model.Izin;
import com.magaza.dukkan.model.YillikIzinHakkiDetaySnapshot;
import jakarta.persistence.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

public interface YillikIzinHakkiDetaySnapshotRepository extends JpaRepository<YillikIzinHakkiDetaySnapshot, Long> {
    public List<YillikIzinHakkiDetaySnapshot> findAllByPersonelId(Long personelId);

}
