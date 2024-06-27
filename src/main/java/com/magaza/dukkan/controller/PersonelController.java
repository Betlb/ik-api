package com.magaza.dukkan.controller;

import com.magaza.dukkan.model.Personel;
import com.magaza.dukkan.service.PersonelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/personel")
public class PersonelController {

    @Autowired
    PersonelService personelService;

//search ayrı

    @GetMapping("/personels")
    public ResponseEntity<Page<Personel>> getPersonels(Pageable pageable) {
        Page<Personel> page = personelService.getAllPersonels(pageable);
        return ResponseEntity.ok(page);
    }

    @PostMapping("/add-personel")
    public ResponseEntity<?> addPersonel(@RequestBody Personel personel) {
        return personelService.addPersonel(personel);
    }

    @PutMapping("/edit")
    public ResponseEntity<?>  editPersonel(@RequestParam Long id, @RequestBody Personel personel){
        return personelService.editPersonel(id, personel);
    }

    @GetMapping("/personel")
    public Personel getPersonel(@RequestParam Long id){
        return personelService.getPersonel(id);
    }

    @DeleteMapping("/delete")
    public boolean deletePersonel(@RequestParam Long id){
        return personelService.deletePersonel(id);
    }


    @GetMapping("/searchPersonel")
    public ResponseEntity<?> searchPersonel(@RequestParam String adSoyad) {
        List<Personel> personels = personelService.searchByAdSoyad(adSoyad);

        if (personels == null || personels.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(personels);
    }

}

