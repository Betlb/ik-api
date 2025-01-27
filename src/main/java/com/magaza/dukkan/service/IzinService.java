package com.magaza.dukkan.service;

import com.magaza.dukkan.dto.PersonelWithIzinDetayDTO;
import com.magaza.dukkan.model.Izin;
import com.magaza.dukkan.model.Personel;
import com.magaza.dukkan.model.YillikIzinHakkiDetay;
import com.magaza.dukkan.model.YillikIzinHakkiDetaySnapshot;
import com.magaza.dukkan.repository.IzinRepository;
import com.magaza.dukkan.repository.PersonelRepository;
import com.magaza.dukkan.repository.YillikIzinHakkiDetayRepository;
import com.magaza.dukkan.repository.YillikIzinHakkiDetaySnapshotRepository;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Service
public class IzinService {

    @Autowired
    final IzinRepository izinRepository;
    @Autowired
    final PersonelService personelService;

    @Autowired
    final PersonelRepository personelRepository;

    @Autowired
    final YillikIzinHakkiDetayRepository yillikIzinHakkiDetayRepository;

    @Autowired
    final YillikIzinHakkiDetaySnapshotRepository yillikIzinHakkiDetaySnapshotRepository;

    public IzinService(IzinRepository izinRepository, PersonelService personelService, PersonelRepository personelRepository, YillikIzinHakkiDetayRepository yillikIzinHakkiDetayRepository, YillikIzinHakkiDetaySnapshotRepository yillikIzinHakkiDetaySnapshotRepository) {
        this.izinRepository = izinRepository;
        this.personelService = personelService;
        this.personelRepository = personelRepository;
        this.yillikIzinHakkiDetayRepository = yillikIzinHakkiDetayRepository;
        this.yillikIzinHakkiDetaySnapshotRepository = yillikIzinHakkiDetaySnapshotRepository;
    }

    public ResponseEntity<?> create(Izin izin) {
        PersonelWithIzinDetayDTO personelWithIzinDetayDTO= personelService.getPersonelWithIzinDetay((izin.getPersonelId()));
        Personel personel = personelWithIzinDetayDTO.getPersonel();
        List<YillikIzinHakkiDetay> yillikIzinHakkiDetay = personelWithIzinDetayDTO.getIzinDetaylar();

        Map<Integer, Long> usedLeavePerYearMap = new HashMap<>();

        if (izin.getIzinTuru().equals("Yıllık İzin")) {
            if (personel.getToplamYillikIzinHakki() < izin.getIzinGun()) {
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("message", "İzin sayınız yetersiz. Kullanılan yıllık izin: " + personel.getToplamYillikIzinHakki());
                return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
            } else {
                int izinGun = izin.getIzinGun();
                yillikIzinHakkiDetay.sort(Comparator.comparing(YillikIzinHakkiDetay::getYil));

                for (YillikIzinHakkiDetay detay : yillikIzinHakkiDetay) {
                    if (izinGun <= 0) break;

                    long kalanIzinHakki = detay.getKalanIzinHakki();
                    long kullanilanIzin = 0;
                    int yil = LocalDateTime.ofInstant(detay.getYil().toInstant(), ZoneId.systemDefault()).getYear();


                    if (kalanIzinHakki >= izinGun) {
                        detay.setKalanIzinHakki(kalanIzinHakki - izinGun);
                        kullanilanIzin = izinGun;
                        izinGun = 0;
                    } else {
                        detay.setKalanIzinHakki(Long.valueOf(0));
                        kullanilanIzin = kalanIzinHakki;
                        izinGun -= kalanIzinHakki;
                    }
                    usedLeavePerYearMap.put(yil, usedLeavePerYearMap.getOrDefault(yil, 0L) + kullanilanIzin);

                    if (detay.getKalanIzinHakki() == 0) {
                        yillikIzinHakkiDetayRepository.delete(detay);
                    } else {
                        yillikIzinHakkiDetayRepository.save(detay);
                    }
                }

                // Update Personel's total leave entitlement
                personel.setToplamYillikIzinHakki(personel.getToplamYillikIzinHakki() - izin.getIzinGun());
                personelRepository.save(personel);
            }
        }
        else {
            personel.setMazeretIzin(personel.getMazeretIzin() + izin.getIzinGun() );
        }
        Izin newIzin = new Izin();
        newIzin.setSebep(izin.getSebep());
        newIzin.setPersonelId(personel.getId());
        newIzin.setIzniVeren(izin.getIzniVeren());
        newIzin.setIzinTuru(izin.getIzinTuru());
        newIzin.setIzinBaslangicTarihi(izin.getIzinBaslangicTarihi());
        newIzin.setIzinBitisTarihi(izin.getIzinBitisTarihi());
        newIzin.setIzinGun(izin.getIzinGun());
        newIzin.setKurumSicilNo(izin.getKurumSicilNo());
        newIzin.setIzinOlusturulmaTarihi(Timestamp.from(Instant.now()));
        newIzin.setGenelTatil(izin.getGenelTatil());
        newIzin.setHaftaTatili(izin.getHaftaTatili());
        newIzin.setToplamYillikIzinHakki(personel.getToplamYillikIzinHakki());
        Izin savedIzin = izinRepository.save(newIzin);

        // Second loop: Create snapshots for each year and fill the map details
        for (YillikIzinHakkiDetay detay : yillikIzinHakkiDetay) {
            // Extract the year part from the Timestamp
            int yil = LocalDateTime.ofInstant(detay.getYil().toInstant(), ZoneId.systemDefault()).getYear();

            YillikIzinHakkiDetaySnapshot snapshot = new YillikIzinHakkiDetaySnapshot();
            snapshot.setPersonelId(personel.getId());
            snapshot.setYil(detay.getYil());  // Set the actual Timestamp as yil
            snapshot.setKalanIzinHakki(detay.getKalanIzinHakki());  // Remaining leave for that year
            snapshot.setYillikIzinHakki(detay.getYillikIzinHakki());  // Total leave for that year

            // Get the used leave from the map
            snapshot.setKullanilanIzinHakki(usedLeavePerYearMap.getOrDefault(yil, 0L));
            snapshot.setIzinId(savedIzin.getId());  // Set the saved izin's ID

            yillikIzinHakkiDetaySnapshotRepository.save(snapshot);  // Save the snapshot
        }


        Map<String, Object> successResponse = new HashMap<>();
        successResponse.put("message", "İzin başarıyla verildi.");
        successResponse.put("izin", savedIzin);

        return ResponseEntity.ok(successResponse);

    }


    public byte[] generateIzinPdf(Long izinId) throws SQLException, JRException, IOException {

        return JasperReportUtil.generatePdfFromJasper(izinId);
    }


    public List<Personel> getPersoneller() {
        return personelService.getAllPersonelsWithIzinDetay();
    }

    public List<Izin> getIzinsWithPersonelId(Personel personel){
        return izinRepository.findAllByPersonelId(personel.getId());
    }

}
