package com.magaza.dukkan.controller;

import com.magaza.dukkan.dto.PersonelWithIzinDetayDTO;
import com.magaza.dukkan.service.PersonelService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/personel")
public class PersonelController {

    @Autowired
    PersonelService personelService;

//search ayrı

    @GetMapping("/personels")
    public ResponseEntity<Page<PersonelWithIzinDetayDTO>> getPersonels(@RequestParam(value = "birimAd", required = false) String birimAd, Pageable pageable) {

        Page<PersonelWithIzinDetayDTO> personels = personelService.getPersonels(birimAd, pageable);
        return ResponseEntity.ok(personels);
    }

    @PostMapping("/pdf")
    public ResponseEntity<byte[]> generateIzinPdf(@RequestParam(required = false) String birimAd, @RequestBody Map<String, Boolean> visibleColumns) {
        try {
            // Pass the visibility info to the service method
            byte[] pdfBytes = personelService.generateIzinPdf(birimAd, visibleColumns);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentDispositionFormData("inline", "personeller.pdf");
            headers.setContentType(MediaType.APPLICATION_PDF);

            return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(pdfBytes);
        } catch (SQLException | JRException | IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/add-personel")
    public ResponseEntity<?> addPersonel(@RequestBody PersonelWithIzinDetayDTO personelWithIzinDetayDTO) {
        return personelService.addPersonel(personelWithIzinDetayDTO);
    }

    @PutMapping("/edit")
    public ResponseEntity<?> editPersonel(@RequestParam Long id, @RequestBody PersonelWithIzinDetayDTO personelWithIzinDetayDTO) {
        return personelService.editPersonel(id, personelWithIzinDetayDTO);
    }

    @GetMapping("/personel")
    public ResponseEntity<PersonelWithIzinDetayDTO> getPersonel(@RequestParam Long id) {
        PersonelWithIzinDetayDTO dto = personelService.getPersonelWithIzinDetay(id);
        return ResponseEntity.ok(dto);
    }


    @DeleteMapping("/delete")
    public ResponseEntity<?> deletePersonel(@RequestParam Long id) {
        boolean result = personelService.deletePersonel(id);
        if (result) {
            return ResponseEntity.ok(new HashMap<String, String>() {{
                put("message", "Personel and associated records deleted successfully.");
            }});
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Personel not found or could not be deleted.");
        }
    }


    @GetMapping("/searchPersonel")
    public ResponseEntity<Page<PersonelWithIzinDetayDTO>> searchPersonel(Pageable pageable, @RequestParam String adi, @RequestParam String soyadi, @RequestParam(value = "birimAd", required = false) String birimAd) {
        Page<PersonelWithIzinDetayDTO> personels = personelService.searchByAdiAndSoyadi(pageable, adi, soyadi, birimAd);

        if (personels == null || personels.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(personels);
    }


}

