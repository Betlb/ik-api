package com.magaza.dukkan.service;
import com.magaza.dukkan.model.Personel;
import com.magaza.dukkan.repository.PersonelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
//personel ve login burda loginde sadce bir sifre username olacak diger personeller icin olmaayack
//user personel degil yonetici ama bu sinif personel sinifi
@Service
public class PersonelService {

    @Autowired
    PersonelRepository personelRepository;



    public Page<Personel> getAllPersonels(Pageable pageable) {
        return personelRepository.findAll(pageable);
    }


    public ResponseEntity<?>  editPersonel(Long id, Personel personel) {

        Personel personel1 = personelRepository.findById(personel.getId()).orElse(null);
        if (personel1 == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Personel not found!");
        }
        personel1.setAdi(personel.getAdi());
        personel1.setAdres(personel.getAdres());
        personel1.setFazlaMesai(personel.getFazlaMesai());
        personel1.setIsGirisTarihi((personel.getIsGirisTarihi()));
        personel1.setMazeretIzin(personel.getMazeretIzin());
        personel1.setMail(personel.getMail());
        personel1.setTel(personel.getTel());
        personel1.setYillikIzin(personel.getYillikIzin());
        personel1.setTc(personel.getTc());

        personelRepository.save(personel1);

        return ResponseEntity.ok(new HashMap<String, String>() {{
            put("message", "Personel updated successfully.");
        }});
    }

    public Personel getPersonel(Long id) {
        return personelRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Personel is not found!!"));
    }



        public ResponseEntity<?> addPersonel(@RequestBody Personel personel) {
            Personel existingPersonel = personelRepository.findUserByTc(personel.getTc());
            if (existingPersonel != null) {
                // Return 409 Conflict if the personel already exists
                return ResponseEntity.status(409).body(new HashMap<String, String>() {{
                    put("message", "User already exists!");
                }});
            }

            personel.setAdSoyad(personel.getAdi() + " " + personel.getSoyadi());
            personelRepository.save(personel);
            // Return 200 OK if the personel is successfully registered
            return ResponseEntity.ok(new HashMap<String, String>() {{
                put("message", "User registered successfully.");
            }});
        }


    public boolean deletePersonel(Long id) {
        personelRepository.deleteById(id);
        return true;
    }

    public List<Personel> getAllPersonels() {
        return personelRepository.findAll();
    }

    public List<Personel> searchByAdSoyadStartingWith(Pageable pageable, String adSoyad) {
        return personelRepository.findByAdSoyadStartingWithIgnoreCase(pageable,adSoyad);
    }
}

