package com.example.demoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demoapi.model.Country;

public interface CountryRepo extends JpaRepository<Country, Long> {

	/**
	 * Method that delete a Country by uuid code
	 * @param code
	 */
	public void deleteByCode(String code);
	
}
