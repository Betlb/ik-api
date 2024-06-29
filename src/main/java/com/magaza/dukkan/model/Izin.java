package com.magaza.dukkan.model;

import jakarta.persistence.*;

import java.sql.Date;
@Entity
@Table(name = "izin", schema = "public")
public class Izin {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "izin_seq")
    @SequenceGenerator(name = "izin_seq", sequenceName = "izin_seq", allocationSize = 1)
    private Long id;

    @Column(name = "sebep")
    private String sebep;

    @Column(name = "personel_id")
    private Long personelId;

    @Column(name = "izni_veren")
    private String izniVeren;


    @Column(name = "izin_turu")
    private String izinTuru;

    @Column(name = "izin_baslangic_tarihi")
    private Date izinBaslangicTarihi;

    @Column(name = "izin_bitis_tarihi")
    private Date izinBitisTarihi;

    @Column(name = "izin_gun")
    private Integer izinGun;

    @Column(name = "izin_saat")
    private Integer izinSaat;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSebep() {
        return sebep;
    }

    public void setSebep(String sebep) {
        this.sebep = sebep;
    }

    public Long getPersonelId() {
        return personelId;
    }

    public void setPersonelId(Long personelId) {
        this.personelId = personelId;
    }

    public String getIzniVeren() {
        return izniVeren;
    }

    public void setIzniVeren(String izniVeren) {
        this.izniVeren = izniVeren;
    }


    public String getIzinTuru() {
        return izinTuru;
    }

    public void setIzinTuru(String izinTuru) {
        this.izinTuru = izinTuru;
    }


    public Date getIzinBaslangicTarihi() {
        return izinBaslangicTarihi;
    }

    public void setIzinBaslangicTarihi(Date izinBaslangicTarihi) {
        this.izinBaslangicTarihi = izinBaslangicTarihi;
    }

    public Date getIzinBitisTarihi() {
        return izinBitisTarihi;
    }

    public void setIzinBitisTarihi(Date izinBitisTarihi) {
        this.izinBitisTarihi = izinBitisTarihi;
    }

    public Integer getIzinGun() {
        return izinGun;
    }

    public void setIzinGun(Integer izinGun) {
        this.izinGun = izinGun;
    }

    public Integer getIzinSaat() {
        return izinSaat;
    }

    public void setIzinSaat(Integer izinSaat) {
        this.izinSaat = izinSaat;
    }
}
