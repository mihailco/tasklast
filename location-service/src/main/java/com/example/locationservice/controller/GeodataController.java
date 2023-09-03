package com.example.locationservice.controller;

import com.example.locationservice.entity.Geodata;
import com.example.locationservice.service.GeodataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class GeodataController {
    @Autowired
    GeodataService geodataService;


    @PostMapping
    public ResponseEntity<?> addGeodata(@RequestBody Geodata geodata){
        try {
            return new ResponseEntity<>(geodataService.addGeodata(geodata), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("city already exist", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<?> getCord(@RequestParam String city){
        return new  ResponseEntity<>(geodataService.getByName(city), HttpStatus.OK);
    }
}
