package com.magaza.dukkan.controller;

import com.magaza.dukkan.model.Urun;
import com.magaza.dukkan.service.UrunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/urun")
public class UrunController {

    @Autowired
    UrunService urunService;

    @GetMapping("/urunler")
    public List<Urun> getUrunler(@RequestParam(value = "query", required = false) String query) {
        if (query != null && !query.isEmpty()) {
            return urunService.findAllByUrunAdiContainingIgnoreCase(query);
        } else {
            return urunService.getAllUrunler();
        }
    }

    @PostMapping("/urunler")
    public Urun urunKaydet(@RequestBody Urun urun) {
        return urunService.urunKaydet(urun);
    }

    @PutMapping("/urunler")
    public Urun urunDuzelt(@RequestParam long urunId, @RequestBody Urun urun) {
        return urunService.urunDuzelt(urunId, urun);
    }

    @DeleteMapping("/delete")
    public boolean removeUrunById(@RequestParam long urunId) {
        return urunService.removeUrunById(urunId);
    }


}
