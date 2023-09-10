package com.example.demoapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demoapi.model.City;
import com.example.demoapi.repository.CityRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CityService {

	private CityRepo repo;
	
	public List<City> findAll() {
		return repo.findAll();
	}
	
	public List<City> findByStateId(Long stateId) {
    	return repo.findByStateId(stateId);
    }
	
	public City save( City city ) {
		return repo.save(city);
	}
	
	public void delete(Long id) {
		repo.deleteById(id);
	}
	
	public void delete(City city) {
		repo.delete(city);
	}
}
