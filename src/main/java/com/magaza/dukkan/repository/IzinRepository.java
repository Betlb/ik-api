package com.magaza.dukkan.repository;

import com.magaza.dukkan.model.Izin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IzinRepository extends JpaRepository<Izin, Long> {
    public List<Izin> findAllByPersonelId(Long personelId);
}
