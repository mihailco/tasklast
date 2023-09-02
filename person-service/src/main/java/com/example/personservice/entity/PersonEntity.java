package com.example.personservice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table
public class PersonEntity {
   @Id
   @GeneratedValue
   private long id;
   private String firstname;
   private String surname;
   private String lastname;
   private LocalDate birthday;
}