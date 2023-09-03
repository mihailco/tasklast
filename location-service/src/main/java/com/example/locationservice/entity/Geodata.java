package com.example.locationservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Geodata {
    @Id
    @GeneratedValue
    int id;
    @NonNull
    private double lon;
    @NonNull
    private double lat;
    @NonNull
    @Column(unique = true)
    private String name;
}
