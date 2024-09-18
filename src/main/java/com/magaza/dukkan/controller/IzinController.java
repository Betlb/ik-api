package com.magaza.dukkan.controller;

import com.magaza.dukkan.dto.PersonelWithIzinDetayDTO;
import com.magaza.dukkan.model.Izin;
import com.magaza.dukkan.model.Personel;
import com.magaza.dukkan.service.IzinService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/izin")
public class IzinController {

    @Autowired
    IzinService izinService;


    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Izin izin){
        return izinService.create(izin);
    }



    @GetMapping("/pdf/{id}")
    public ResponseEntity<byte[]> generateIzinPdf(@PathVariable Long id) {
        try {
            byte[] pdfBytes = izinService.generateIzinPdf(id);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentDispositionFormData("inline", "izin_raporu.pdf");
            headers.setContentType(MediaType.APPLICATION_PDF);

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(pdfBytes);
        } catch (SQLException | JRException | IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @GetMapping("/getPersoneller")
    public List<PersonelWithIzinDetayDTO> getPersoneller(){
        return izinService.getPersoneller();
    }
    

    @PostMapping("/getIzinsWithPersonelId")
    public List<Izin> getIzinsWithPersonelId(@RequestBody Personel personel){
        return izinService.getIzinsWithPersonelId(personel);
    }




}
