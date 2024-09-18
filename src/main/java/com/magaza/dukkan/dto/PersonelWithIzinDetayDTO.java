package com.magaza.dukkan.dto;

import com.magaza.dukkan.model.Personel;
import com.magaza.dukkan.model.YillikIzinHakkiDetay;

import java.util.List;

public class PersonelWithIzinDetayDTO {

    private Personel personel;
    private List<YillikIzinHakkiDetay> izinDetaylar;

    // getters and setters

    public Personel getPersonel() {
        return personel;
    }

    public void setPersonel(Personel personel) {
        this.personel = personel;
    }

    public List<YillikIzinHakkiDetay> getIzinDetaylar() {
        return izinDetaylar;
    }

    public void setIzinDetaylar(List<YillikIzinHakkiDetay> izinDetaylar) {
        this.izinDetaylar = izinDetaylar;
    }
}

