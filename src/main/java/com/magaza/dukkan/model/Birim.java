package com.magaza.dukkan.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "birim", schema = "public")
public class Birim {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "birim_seq")
    @SequenceGenerator(name = "birim_seq", sequenceName = "birim_seq", allocationSize = 1)
    private Long id;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Birim() {}

    public Birim(String ad) {
        this.ad = ad;
    }

    @Column(name = "ad")
    private String ad;

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public Long getId() {
        return id;
    }
}
