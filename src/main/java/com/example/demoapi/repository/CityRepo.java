package com.example.demoapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demoapi.model.City;

public interface CityRepo extends JpaRepository<City, Long> {

	List<City> findByStateId(Long stateId);
	
}
