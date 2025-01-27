package com.magaza.dukkan.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name= "personel",schema = "public")
public class Personel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "personel_seq")
    @SequenceGenerator(name = "personel_seq", sequenceName = "personel_seq", allocationSize = 1)
    private Long id;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(name="adi")
    private String adi;
    @Column(name="soyadi")
    private String soyadi;

    @Column(name = "tc")
    private String tc;

    @Column(name = "adres")
    private String adres;

    @Column(name = "tel")
    private String tel;

    @Column(name = "mail")
    private  String mail;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
    @Column(name = "is_giris_tarihi")
    private Timestamp isGirisTarihi;

    @Column(name = "mazeret_izin")
    private  Long mazeretIzin;

    @Column(name = "gorev")
    private String gorev;

    @Column(name = "birim_ad")
    private String birimAd;

    @Column(name = "toplam_yillik_izin_hakki")
    private  Long toplamYillikIzinHakki;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdi() {
        return adi;
    }

    public void setAdi(String adi) {
        this.adi = adi;
    }

    public String getSoyadi() {
        return soyadi;
    }

    public void setSoyadi(String soyadi) {
        this.soyadi = soyadi;
    }

    public String getTc() {
        return tc;
    }

    public void setTc(String tc) {
        this.tc = tc;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }


    public Timestamp getIsGirisTarihi() {
        return isGirisTarihi;
    }

    public void setIsGirisTarihi(Timestamp isGirisTarihi) {
        this.isGirisTarihi = isGirisTarihi;
    }

    public Long getMazeretIzin() {
        return mazeretIzin;
    }

    public void setMazeretIzin(Long mazeretIzin) {
        this.mazeretIzin = mazeretIzin;
    }

    public String getGorev() {
        return gorev;
    }

    public void setGorev(String gorev) {
        this.gorev = gorev;
    }

    public String getBirimAd() {
        return birimAd;
    }

    public void setBirimAd(String birimAd) {
        this.birimAd = birimAd;
    }

    public Long getToplamYillikIzinHakki() {
        return toplamYillikIzinHakki;
    }

    public void setToplamYillikIzinHakki(Long toplamYillikIzinHakki) {
        this.toplamYillikIzinHakki = toplamYillikIzinHakki;
    }

}
