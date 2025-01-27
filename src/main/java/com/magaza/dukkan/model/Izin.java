package com.magaza.dukkan.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "izin", schema = "public")
public class Izin {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "izin_seq")
    @SequenceGenerator(name = "izin_seq", sequenceName = "izin_seq", allocationSize = 1)
    private Long id;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(name = "sebep")
    private String sebep;

    @Column(name = "personel_id")
    private Long personelId;

    @Column(name = "izni_veren")
    private String izniVeren;


    @Column(name = "izin_turu")
    private String izinTuru;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
    @Column(name = "izin_baslangic_tarihi")
    private Timestamp izinBaslangicTarihi;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
    @Column(name = "izin_bitis_tarihi")
    private Timestamp izinBitisTarihi;

    @Column(name = "izin_gun")
    private Integer izinGun;

    @Column(name = "hafta_tatili")
    private Long haftaTatili;

    @Column(name = "genel_tatil")
    private Long genelTatil;

    @Column(name = "izin_olusturulma_tarihi")
    private Timestamp izinOlusturulmaTarihi;

    @Column(name = "kurum_sicil_no")
    private Long kurumSicilNo;

    @Column(name = "toplam_yillik_izin_hakki")
    private Long toplamYillikIzinHakki;



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


    public Timestamp getIzinBaslangicTarihi() {
        return izinBaslangicTarihi;
    }

    public void setIzinBaslangicTarihi(Timestamp izinBaslangicTarihi) {
        this.izinBaslangicTarihi = izinBaslangicTarihi;
    }

    public Timestamp getIzinBitisTarihi() {
        return izinBitisTarihi;
    }

    public void setIzinBitisTarihi(Timestamp izinBitisTarihi) {
        this.izinBitisTarihi = izinBitisTarihi;
    }

    public Integer getIzinGun() {
        return izinGun;
    }

    public void setIzinGun(Integer izinGun) {
        this.izinGun = izinGun;
    }


    public Timestamp getIzinOlusturulmaTarihi() {
        return izinOlusturulmaTarihi;
    }

    public void setIzinOlusturulmaTarihi(Timestamp izinOlusturulmaTarihi) {
        this.izinOlusturulmaTarihi = izinOlusturulmaTarihi;
    }

    public Long getKurumSicilNo() {
        return kurumSicilNo;
    }

    public void setKurumSicilNo(Long kurumSicilNo) {
        this.kurumSicilNo = kurumSicilNo;
    }

    public Long getHaftaTatili() {
        return haftaTatili;
    }

    public void setHaftaTatili(Long haftaTatili) {
        this.haftaTatili = haftaTatili;
    }

    public Long getGenelTatil() {
        return genelTatil;
    }

    public void setGenelTatil(Long genelTatil) {
        this.genelTatil = genelTatil;
    }

    public Long getToplamYillikIzinHakki() {
        return toplamYillikIzinHakki;
    }

    public void setToplamYillikIzinHakki(Long toplamYillikIzinHakki) {
        this.toplamYillikIzinHakki = toplamYillikIzinHakki;
    }
}
