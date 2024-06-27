package com.magaza.dukkan.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name= "personel",schema = "public")
public class Personel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "personel_seq")
    @SequenceGenerator(name = "personel_seq", sequenceName = "personel_seq", allocationSize = 1)
    private Long id;

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


    @Column(name = "is_giris_tarihi")
    private Date isGirisTarihi;

    @Column(name = "is_cikis_tarihi")
    private Date isCikisTarihi;

    @Column(name = "mazeret_izin")
    private  Long mazeretIzin;

    @Column(name = "yillik_izin")
    private  Long yillikIzin;

    @Column(name = "fazla_mesai")
    private Integer fazlaMesai;

    @Column(name = "ad_soyad")
    private String adSoyad;

    public String getAdSoyad() {
        return adSoyad;
    }

    public void setAdSoyad(String adSoyad) {
        this.adSoyad = adSoyad;
    }

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


    public Date getIsGirisTarihi() {
        return isGirisTarihi;
    }

    public void setIsGirisTarihi(Date isGirisTarihi) {
        this.isGirisTarihi = isGirisTarihi;
    }

    public Date getIsCikisTarihi() {
        return isCikisTarihi;
    }

    public void setIsCikisTarihi(Date isCikisTarihi) {
        this.isCikisTarihi = isCikisTarihi;
    }

    public Long getMazeretIzin() {
        return mazeretIzin;
    }

    public void setMazeretIzin(Long mazeretIzin) {
        this.mazeretIzin = mazeretIzin;
    }

    public Long getYillikIzin() {
        return yillikIzin;
    }

    public void setYillikIzin(Long yillikIzin) {
        this.yillikIzin = yillikIzin;
    }

    public Integer getFazlaMesai() {
        return fazlaMesai;
    }

    public void setFazlaMesai(Integer fazlaMesai) {
        this.fazlaMesai = fazlaMesai;
    }

    @Override
    public String toString() {
        return "{" +
                " \"adi\":\"" + adi + "\"," +
                " \"soyadi\":\"" + soyadi + "\"" +
                '}';
    }
}
