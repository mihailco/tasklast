package com.example.locationservice.controller;

import com.example.locationservice.entity.Geodata;
import com.example.locationservice.model.Weather;
import com.example.locationservice.service.GeodataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class GeodataController {
    @Autowired
    GeodataService geodataService;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${url.services.weather}")
    private String weatherServiceUrl;


    @PostMapping
    public ResponseEntity<?> addGeodata(@RequestBody Geodata geodata) {
        try {
            return new ResponseEntity<>(geodataService.addGeodata(geodata), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("city already exist", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<?> getCord(@RequestParam String city) {
        return new ResponseEntity<>(geodataService.getByName(city), HttpStatus.OK);
    }

    @GetMapping("/weather")
    public Weather redirectRequestWeather(@RequestParam String location) {
        Geodata geodata = geodataService.getByName(location);
        String url = String.format("%s/?lat=%s&lon=%s", weatherServiceUrl, geodata.getLat(), geodata.getLon());
        return restTemplate.getForObject(url, Weather.class);
    }
}
