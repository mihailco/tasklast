package com.example.personservice.repository;

import com.example.personservice.entity.PersonEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface PersonRepository extends CrudRepository<PersonEntity, Long> {
   @Modifying
   @Transactional
   @Query("update PersonEntity set lastname = :ln, birthday = :bd, surname = :sn, firstname = :fn where id = :id")
   void modifyById(@Param("id") long id, @Param("fn") String firstname,
                   @Param("ln") String lastname, @Param("sn") String surname,
                   @Param("bd") LocalDate birthday);
}
