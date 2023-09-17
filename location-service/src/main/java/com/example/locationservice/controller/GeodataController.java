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

import static org.springframework.http.HttpStatus.OK;

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

    @GetMapping("/cities")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(geodataService.getAll(), OK);
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

    @PutMapping("/{name}")
    public ResponseEntity<?> updateGeodata(@PathVariable String name, @RequestBody Geodata geodata) {

        try {
            return new ResponseEntity<>(geodataService.updateGeodata(name, geodata), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<?> deleteLocation(@PathVariable String name) {
        if (geodataService.deleteGeo(name) == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("deleted", OK);
    }
}
