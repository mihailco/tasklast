package com.example.locationservice.repository;

import com.example.locationservice.entity.Geodata;
import lombok.NonNull;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface GeodataRepository extends CrudRepository<Geodata, Integer> {

    Optional<Geodata> findByName(@NonNull String name);

    @Transactional
    @Modifying
    int removeByName(String name);

    @Transactional
    @Modifying
    @Query("update Geodata  set lat = :#{#g.lat}, lon = :#{#g.lon} where name = ?1")
    int update(String name, @Param("g") Geodata geodata);
}
