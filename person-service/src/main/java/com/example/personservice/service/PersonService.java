package com.example.personservice.service;

import com.example.personservice.entity.PersonEntity;
import com.example.personservice.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

   @Autowired
   PersonRepository personRepository;


   public PersonEntity save(PersonEntity person) {
      return personRepository.save(person);
   }

   public Iterable<PersonEntity> getAll() {
      return personRepository.findAll();
   }

   public void modifyUser(long id, PersonEntity person) {
      personRepository.modifyById(id, person.getFirstname(), person.getLastname(), person.getSurname(), person.getBirthday());
   }

   public void deletePerson(long id) {
      personRepository.deleteById(id);
   }
}
