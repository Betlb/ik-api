package com.magaza.dukkan.service;

import com.magaza.dukkan.model.YillikIzinHakkiDetay;
import com.magaza.dukkan.repository.YillikIzinHakkiDetayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class YillikIzinHakkiDetayService {

    @Autowired
    YillikIzinHakkiDetayRepository yillikIzinHakkiDetayRepository;
}
