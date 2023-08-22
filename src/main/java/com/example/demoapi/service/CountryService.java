package com.example.demoapi.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.demoapi.model.Country;
import com.example.demoapi.repository.CountryRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CountryService {

	private CountryRepo repo;
	
	public Optional<Country> findById(Long id) {
		return repo.findById(id);
	}
	
	public List<Country> findAll() {
		return repo.findAll();
	}
	
	public Country save(Country ent) {
		return repo.save(ent);
	}
	
	public void delete(Long id) {
		repo.deleteById(id);
	}
	
	public void delete(Country country) {
		repo.delete(country);
	}
	
	@Transactional
	public void deleteByCode(String uuid) {
		repo.deleteByCode(uuid);
	}
}
