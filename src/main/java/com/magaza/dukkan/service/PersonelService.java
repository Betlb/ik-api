package com.magaza.dukkan.service;
import com.magaza.dukkan.dto.PersonelWithIzinDetayDTO;
import com.magaza.dukkan.model.Personel;
import com.magaza.dukkan.model.YillikIzinHakkiDetay;
import com.magaza.dukkan.repository.BirimRepository;
import com.magaza.dukkan.repository.PersonelRepository;
import com.magaza.dukkan.repository.YillikIzinHakkiDetayRepository;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class PersonelService {

    @Autowired
    PersonelRepository personelRepository;

    @Autowired
    YillikIzinHakkiDetayRepository yillikIzinHakkiDetayRepository;

    @Autowired
    BirimRepository birimRepository;


    public Page<PersonelWithIzinDetayDTO> getPersonels(String birimAd, Pageable pageable) {
        Page<Personel> personels;

        if (birimAd != null && !birimAd.isEmpty()) {
            // If birimAd is provided, filter by birim
            personels = personelRepository.findByBirimAd(birimAd, pageable);
        } else {
            // If birimAd is not provided, fetch all personnel
            personels = personelRepository.findAll(pageable);
        }

        // Map the results to DTO with izin detaylar
        return personels.map(personel -> {
            List<YillikIzinHakkiDetay> izinDetaylar = yillikIzinHakkiDetayRepository.findByPersonelId(personel.getId());
            PersonelWithIzinDetayDTO dto = new PersonelWithIzinDetayDTO();
            dto.setPersonel(personel);
            dto.setIzinDetaylar(izinDetaylar);
            return dto;
        });
    }



    public ResponseEntity<?>  editPersonel(Long id, PersonelWithIzinDetayDTO personelWithIzinDetayDTO) {
        // Find the existing personnel record
        Personel personel1 = personelRepository.findById(id).orElse(null);
        if (personel1 == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Personel not found!");
        }

        // Extract Personel and YillikIzinHakkiDetaylar from the DTO
        Personel updatedPersonel = personelWithIzinDetayDTO.getPersonel();
        List<YillikIzinHakkiDetay> updatedIzinDetaylar = personelWithIzinDetayDTO.getIzinDetaylar();

        // Update the existing personnel's details only if the fields are not null
        if (updatedPersonel.getAdi() != null) personel1.setAdi(updatedPersonel.getAdi());
        if (updatedPersonel.getSoyadi() != null) personel1.setSoyadi(updatedPersonel.getSoyadi());
        if (updatedPersonel.getAdres() != null) personel1.setAdres(updatedPersonel.getAdres());
        if (updatedPersonel.getIsGirisTarihi() != null) personel1.setIsGirisTarihi(updatedPersonel.getIsGirisTarihi());
        if (updatedPersonel.getMazeretIzin() != null) personel1.setMazeretIzin(updatedPersonel.getMazeretIzin());
        if (updatedPersonel.getMail() != null) personel1.setMail(updatedPersonel.getMail());
        if (updatedPersonel.getTel() != null) personel1.setTel(updatedPersonel.getTel());
        if (updatedPersonel.getTc() != null) personel1.setTc(updatedPersonel.getTc());
        // Check if the new birimAd exists in the Birim table
        if (updatedPersonel.getBirimAd() != null) {
            List<String> existingBirimNames = birimRepository.findAllAd();

            // Check if the list of birim names contains the updated person's birim name
            boolean birimExists = existingBirimNames.stream()
                    .anyMatch(birimName -> birimName.equalsIgnoreCase(updatedPersonel.getBirimAd()));

            if (!birimExists) {
                // Option 1: Add a new Birim if it does not exist
                // birimService.addBirim(updatedPersonel.getBirimAd());

                // Option 2: Return an error if birimAd does not exist
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Birim not found!");
            }

            // If the Birim exists, set the BirimAd for personel1
            personel1.setBirimAd(updatedPersonel.getBirimAd());
        }
        if (updatedPersonel.getGorev() != null) personel1.setGorev(updatedPersonel.getGorev());

        // Delete existing izinDetaylar for the personnel
        List<YillikIzinHakkiDetay> existingDetaylar = yillikIzinHakkiDetayRepository.findByPersonelId(personel1.getId());

        //izinDetaylarını sil
        for (YillikIzinHakkiDetay detay : existingDetaylar) {
            yillikIzinHakkiDetayRepository.delete(detay);
        }

        // Variable to store the sum of kalanIzinHakki
        Long totalKalanIzinHakki = 0L;

        // If izinDetaylar is not null, add the new ones and calculate the total kalanIzinHakki
        if (updatedIzinDetaylar != null) {
            for (YillikIzinHakkiDetay detay : updatedIzinDetaylar) {
                detay.setPersonelId(personel1.getId()); // Associate the detay with the personel
                detay.setKalanIzinHakki(detay.getYillikIzinHakki());
                yillikIzinHakkiDetayRepository.save(detay);

                // Add the kalanIzinHakki to the total
                totalKalanIzinHakki += detay.getKalanIzinHakki() != null ? detay.getKalanIzinHakki() : 0;
            }
        }

        // Update the personel's yillikIzinHakki with the total kalanIzinHakki
        personel1.setToplamYillikIzinHakki(totalKalanIzinHakki);
        // Save the updated personnel record
        personelRepository.save(personel1);

        return ResponseEntity.ok(new HashMap<String, String>() {{
            put("message", "Personel updated successfully.");
        }});

    }


    public void deletePersonelsWithIzinDetayByBirimAd(String birimAd) {
        List<Personel> personels = personelRepository.findByBirimAd(birimAd);

        for (Personel personel : personels) {
            // Delete the associated izin detay records
            List<YillikIzinHakkiDetay> existingDetaylar = yillikIzinHakkiDetayRepository.findByPersonelId(personel.getId());

            // Tek tek izinDetaylarını sil
            for (YillikIzinHakkiDetay detay : existingDetaylar) {
                yillikIzinHakkiDetayRepository.delete(detay);
            }
            personelRepository.delete(personel);
        }
    }

    public PersonelWithIzinDetayDTO getPersonelWithIzinDetay(Long id) {
        Personel personel = personelRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Personel is not found!!"));

        List<YillikIzinHakkiDetay> izinDetaylar = yillikIzinHakkiDetayRepository.findByPersonelId(personel.getId());


        PersonelWithIzinDetayDTO dto = new PersonelWithIzinDetayDTO();
        dto.setPersonel(personel);
        dto.setIzinDetaylar(izinDetaylar);

        return dto;
    }




    public ResponseEntity<?> addPersonel(@RequestBody PersonelWithIzinDetayDTO personelWithIzinDetayDTO) {
        Personel personel = personelWithIzinDetayDTO.getPersonel();
        List<YillikIzinHakkiDetay> izinDetaylar = personelWithIzinDetayDTO.getIzinDetaylar();

        // Check if personel already exists
        Personel existingPersonel = personelRepository.findUserByTc(personel.getTc());
        if (existingPersonel != null) {
            return ResponseEntity.status(409).body(new HashMap<String, String>() {{
                put("message", "User already exists!");
            }});
        }

        // Check if the birimAd exists in the Birim table
        String birimAd = personel.getBirimAd();
        if (birimAd != null && !birimAd.isEmpty()) {
            List<String> existingBirimNames = birimRepository.findAllAd();

            // Check if the list of birim names contains the personel's birim name
            boolean birimExists = existingBirimNames.stream()
                    .anyMatch(existingBirimName -> existingBirimName.equalsIgnoreCase(birimAd));

            if (!birimExists) {
                // Option 1: Add a new Birim if it does not exist
                // birimService.addBirim(birimAd);

                // Option 2: Return an error if birimAd does not exist
                return ResponseEntity.status(400).body(new HashMap<String, String>() {{
                    put("message", "Birim not found!");
                }});
            }

            // If the Birim exists, set the BirimAd for personel
            personel.setBirimAd(birimAd);
        }


        personelRepository.save(personel);

        long totalYillikIzinHakki = 0;
        // Save annual leave details
        if (izinDetaylar != null) {
            for (YillikIzinHakkiDetay detay : izinDetaylar) {
                totalYillikIzinHakki += detay.getYillikIzinHakki();

                detay.setPersonelId(personel.getId());
                detay.setKalanIzinHakki(detay.getYillikIzinHakki());
                yillikIzinHakkiDetayRepository.save(detay);
            }
        }
        personel.setToplamYillikIzinHakki(totalYillikIzinHakki);
        personelRepository.save(personel);
        return ResponseEntity.ok(new HashMap<String, String>() {{
            put("message", "User registered successfully.");
        }});
    }
    public boolean deletePersonel(Long id) {
        // Check if the Personel exists
        Personel personel = personelRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Personel is not found!!"));

        // Delete associated YillikIzinHakkiDetay records
        List<YillikIzinHakkiDetay> izinDetaylar = yillikIzinHakkiDetayRepository.findByPersonelId(personel.getId());
        if (!izinDetaylar.isEmpty()) {
            yillikIzinHakkiDetayRepository.deleteAll(izinDetaylar);
        }

        // Delete the Personel
        personelRepository.deleteById(id);

        return true;
    }


    public List<PersonelWithIzinDetayDTO> getAllPersonelsWithIzinDetay() {
        List<Personel> personels = personelRepository.findAll();

        // Map each Personel to PersonelWithIzinDetayDTO
        return personels.stream().map(personel -> {
            List<YillikIzinHakkiDetay> izinDetaylar = yillikIzinHakkiDetayRepository.findByPersonelId(personel.getId());
            PersonelWithIzinDetayDTO dto = new PersonelWithIzinDetayDTO();
            dto.setPersonel(personel);
            dto.setIzinDetaylar(izinDetaylar);
            return dto;
        }).collect(Collectors.toList());
    }


    public Page<PersonelWithIzinDetayDTO> searchByAdiAndSoyadi(Pageable pageable, String adi, String soyadi, String birimAd) {
        Page<Personel> personels;

        if (birimAd == null || birimAd.isEmpty()) {
            personels = personelRepository.findByAdiStartingWithIgnoreCaseAndSoyadiStartingWithIgnoreCase(pageable, adi, soyadi);
        } else {
            personels = personelRepository.findByAdiStartingWithIgnoreCaseAndSoyadiStartingWithIgnoreCaseAndBirimAd(pageable, adi, soyadi, birimAd);
        }

        return personels.map(personel -> {
            List<YillikIzinHakkiDetay> izinDetaylar = yillikIzinHakkiDetayRepository.findByPersonelId(personel.getId());
            PersonelWithIzinDetayDTO dto = new PersonelWithIzinDetayDTO();
            dto.setPersonel(personel);
            dto.setIzinDetaylar(izinDetaylar);
            return dto;
        });
    }


    public byte[] generateIzinPdf(String birimAd, Map<String, Boolean> visibleColumns) throws SQLException, JRException, IOException {

        return JasperReportUtil.generatePdfFromPersonelList(birimAd, visibleColumns);
    }

}

