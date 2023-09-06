package com.example.personservice.controller;


import com.example.personservice.entity.PersonEntity;
import com.example.personservice.model.Weather;
import com.example.personservice.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import static org.springframework.http.HttpStatus.OK;

@RestController
public class PersonController {
    @Autowired
    PersonService personService;

    @Value("${url.services.weather}")
    private String weatherServiceUrl;
    @Value("${url.services.location}")
    private String locationServiceUrl;
    @Autowired
    RestTemplate restTemplate;

    @PostMapping
    public ResponseEntity<?> addPerson(@RequestBody PersonEntity person) {
        return new ResponseEntity<>(personService.save(person), OK);
    }


    @GetMapping
    public ResponseEntity<?> getAllPersons() {
        return new ResponseEntity<>(personService.getAll(), OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modifyPerson(@PathVariable long id, @RequestBody PersonEntity person) {
        personService.modifyUser(id, person);
        return new ResponseEntity<>("success", OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable long id) {
        personService.deletePerson(id);
        return new ResponseEntity<>("deleted", OK);
    }

    @GetMapping("/{id}/weather")
    public ResponseEntity<?> getWeather(@PathVariable Long id) {
        if (!personService.existsById(id))
            return new ResponseEntity<>("нет пользователя", HttpStatus.NOT_FOUND);

        String location = personService.findById(id).getLocation();
        Weather weather = restTemplate.getForObject(
                locationServiceUrl + "/weather?location=" + location,
                Weather.class);

        return new ResponseEntity<>(weather, HttpStatus.OK);
    }


}
