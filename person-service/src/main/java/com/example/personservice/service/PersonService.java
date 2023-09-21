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

    public int modifyUser(long id, PersonEntity person) {
       return personRepository.modifyById(id, person.getFirstname(), person.getLastname(), person.getSurname(), person.getBirthday(), person.getLocation());
    }

    public int deletePerson(long id) {
       return personRepository.removeById(id);
    }

    public boolean existsById(Long id) {
        return personRepository.existsById(id);
    }

    public PersonEntity findById(Long id) {

        return personRepository.findById(id).get();
    }

}
