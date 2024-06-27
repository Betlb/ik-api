package com.magaza.dukkan.service;

import com.magaza.dukkan.model.Urun;
import com.magaza.dukkan.repository.UrunRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UrunService {

    final UrunRepository urunRepository;

    public UrunService(UrunRepository urunRepository) {
        this.urunRepository = urunRepository;
    }

    public List<Urun> getAllUrunler() {
        return urunRepository.findAll();
    }

    public boolean removeUrunById(long urunId) {
        urunRepository.deleteById(urunId);
        return true;
    }

    public Urun urunKaydet(Urun urun) {
        return urunRepository.save(urun);
    }

    public Urun urunDuzelt(long urunId, Urun urunData) {
        Urun urun = urunRepository.getReferenceById(urunId);
        urun.setUrunAdi(urunData.getUrunAdi());
        urun.setAdet(urunData.getAdet());
        urun.setFiyat(urunData.getFiyat());
        urun.setTarih(urunData.getTarih());
        return urunRepository.save(urun);
    }

    public List<Urun> findAllByUrunAdiContainingIgnoreCase(String query) {
        return urunRepository.findAllByUrunAdiContainingIgnoreCase(query);
    }
}
