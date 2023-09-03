package com.example.weatherservice.controller;

import com.example.weatherservice.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {

    @Autowired
    WeatherService weatherService;

    @GetMapping
    public ResponseEntity<?> getWeather(@RequestParam String lat, @RequestParam String lon) {
        return new ResponseEntity<>(weatherService.getWeatherByCord(lon, lat), HttpStatus.OK);
    }

    @GetMapping("/city")
    public ResponseEntity<?> getWeatherByCityName(@RequestParam String cityName) {
        return new ResponseEntity<>(weatherService.getWeatherByCityName(cityName), HttpStatus.OK);
    }

}
