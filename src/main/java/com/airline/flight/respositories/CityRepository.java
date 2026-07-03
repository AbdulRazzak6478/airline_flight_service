package com.airline.flight.respositories;

import com.airline.flight.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface CityRepository extends JpaRepository<City, UUID> {

    public Boolean existsByName(String name);
}
