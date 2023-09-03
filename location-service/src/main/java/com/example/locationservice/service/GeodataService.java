package com.example.locationservice.service;

import com.example.locationservice.entity.Geodata;
import com.example.locationservice.repository.GeodataRepository;
import jakarta.ws.rs.core.GenericEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class GeodataService {

    @Autowired
    GeodataRepository geodataRepository;

    public Geodata addGeodata(Geodata geodata) {
        return geodataRepository.save(geodata);
    }

    public Geodata getByName(String cityName) {
        return geodataRepository.findByName(cityName);
    }
}
