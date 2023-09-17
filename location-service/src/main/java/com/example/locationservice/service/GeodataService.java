package com.example.locationservice.service;

import com.example.locationservice.entity.Geodata;
import com.example.locationservice.repository.GeodataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeodataService {

    @Autowired
    GeodataRepository geodataRepository;

    public Geodata addGeodata(Geodata geodata) {
        return geodataRepository.save(geodata);
    }

    public Geodata getByName(String cityName) {
        return geodataRepository.findByName(cityName).get();
    }

    public long deleteGeo(String name) {
         return geodataRepository.deleteByName(name);
    }

    public Iterable<Geodata> getAll() {
        return geodataRepository.findAll();
    }

    public long updateGeodata(String name, Geodata geodata){
        return geodataRepository.update(name, geodata);
    }
}
