package com.magaza.dukkan.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="urun",schema = "public")
public class Urun {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "urun_seq")
    @SequenceGenerator(name = "urun_seq", sequenceName = "urun_seq", allocationSize = 1)
    private Long id;

    @Column(name="urun_adi")
    private String urunAdi;
    @Column(name="adet")
    private Integer adet;
    @Column(name="fiyat")
    private Double fiyat;
    @Column(name="tarih")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date tarih;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrunAdi() {
        return urunAdi;
    }

    public void setUrunAdi(String urunAdi) {
        this.urunAdi = urunAdi;
    }

    public Integer getAdet() {
        return adet;
    }

    public void setAdet(Integer adet) {
        this.adet = adet;
    }

    public Double getFiyat() {
        return fiyat;
    }

    public void setFiyat(Double fiyat) {
        this.fiyat = fiyat;
    }

    public Date getTarih() {
        return tarih;
    }

    public void setTarih(Date tarih) {
        this.tarih = tarih;
    }
}
