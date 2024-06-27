package com.magaza.dukkan.service;

import com.magaza.dukkan.model.Izin;
import com.magaza.dukkan.model.Personel;
import com.magaza.dukkan.repository.IzinRepository;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.Console;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class IzinService {

    @Autowired
    final IzinRepository izinRepository;
    @Autowired
    final PersonelService personelService;

    public IzinService(IzinRepository izinRepository, PersonelService personelService) {
        this.izinRepository = izinRepository;
        this.personelService = personelService;
    }

    public Izin create(Izin izin) {
        Personel personel = personelService.getPersonel(izin.getPersonelId());


        final long yillikIzin = 15L;
        if(izin.getIzinTuru().equals("Yıllık İzin")){
            long tempYillikİzin = personel.getYillikIzin() + izin.getIzinGun();
            if(tempYillikİzin>yillikIzin)
                return  null;//izin oluştutulamadi dmek. yillik izin yetersiz oldugu icin
            else{
                personel.setYillikIzin(tempYillikİzin);
            }
        }
        Izin newIzin = new Izin();
        newIzin.setSebep(izin.getSebep());
        newIzin.setPersonelId(personel.getId());
        newIzin.setIzniVeren(izin.getIzniVeren());
        newIzin.setIzinTuru(izin.getIzinTuru());
        newIzin.setIzinBaslangicTarihi(izin.getIzinBaslangicTarihi());
        newIzin.setIzinBitisTarihi(izin.getIzinBitisTarihi());
        newIzin.setIzinGun(izin.getIzinGun());
        newIzin.setIzinSaat(izin.getIzinSaat());

        return izinRepository.save(newIzin);
    }

    public Izin getIzinById(Long id) {
        Izin izin = izinRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Izin is not found!!"));
        return izin;
    }


    public byte[] generateIzinPdf(Long izinId) throws SQLException, JRException, IOException {

        return JasperReportUtil.generatePdfFromJasper(izinId);
    }


    public List<Personel> getPersoneller() {
        return personelService.getAllPersonels();
    }


}
