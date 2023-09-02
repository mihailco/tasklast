package com.example.personservice.controller;


import com.example.personservice.entity.PersonEntity;
import com.example.personservice.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
public class PersonController {
   @Autowired
   PersonService personService;

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
      return new ResponseEntity("success", OK);
   }

   @DeleteMapping("/{id}")
   public ResponseEntity<?> deletePerson(@PathVariable long id) {
      personService.deletePerson(id);
      return new ResponseEntity<>("deleted", OK);
   }


}
