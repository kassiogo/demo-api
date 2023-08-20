package com.example.demoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demoapi.model.City;

public interface CityRepo extends JpaRepository<City, Long> {

}
