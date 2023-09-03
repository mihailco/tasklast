package com.example.locationservice.repository;

import com.example.locationservice.entity.Geodata;
import lombok.NonNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeodataRepository extends CrudRepository<Geodata, Integer> {
    Geodata findByName(@NonNull String name);
}
