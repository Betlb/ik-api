package com.magaza.dukkan.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name= "yillik_izin_hakki_detay_snapshot",schema = "public")
public class YillikIzinHakkiDetaySnapshot {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "yillik_izin_hakki_detay_snapshot_seq")
    @SequenceGenerator(name = "yillik_izin_hakki_detay_snapshot_seq", sequenceName = "yillik_izin_hakki_detay_snapshot_seq", allocationSize = 1)
    private Long id;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(name = "personel_id", nullable = false)
    private Long personelId;

    @Column(name = "izin_id", nullable = false)
    private Long izinId;

    @Column(name = "yil")
    private Timestamp yil;

    @Column(name = "kalan_izin_hakki")
    private Long kalanIzinHakki;

    @Column(name = "yillik_izin_hakki")
    private Long yillikIzinHakki;

    @Column(name = "kullanilan_izin_hakki")
    private  Long kullanilanIzinHakki;

    public Long getPersonelId() {
        return personelId;
    }

    public void setPersonelId(Long personelId) {
        this.personelId = personelId;
    }

    public Long getIzinId() {
        return izinId;
    }

    public void setIzinId(Long izinId) {
        this.izinId = izinId;
    }

    public Timestamp getYil() {
        return yil;
    }

    public void setYil(Timestamp yil) {
        this.yil = yil;
    }

    public Long getKalanIzinHakki() {
        return kalanIzinHakki;
    }

    public void setKalanIzinHakki(Long kalanIzinHakki) {
        this.kalanIzinHakki = kalanIzinHakki;
    }

    public Long getYillikIzinHakki() {
        return yillikIzinHakki;
    }

    public void setYillikIzinHakki(Long yillikIzinHakki) {
        this.yillikIzinHakki = yillikIzinHakki;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getKullanilanIzinHakki() {
        return kullanilanIzinHakki;
    }

    public void setKullanilanIzinHakki(Long kullanilanIzinHakki) {
        this.kullanilanIzinHakki = kullanilanIzinHakki;
    }
}
