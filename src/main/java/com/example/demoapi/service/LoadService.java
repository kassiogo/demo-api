package com.example.demoapi.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demoapi.dto.load.JsonCountryDTO;
import com.example.demoapi.model.Country;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LoadService {

	private CountryService countryService;
	
	public List<JsonCountryDTO> findAll() {
		TypeReference<List<JsonCountryDTO>> typeReference = new TypeReference<List<JsonCountryDTO>>(){};
		InputStream inputStream = TypeReference.class.getResourceAsStream("/json/countries-states-cities.json");
		List<JsonCountryDTO> dtos = new ArrayList<>();
		try {
			ObjectMapper objMapper = new ObjectMapper();
			objMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			dtos = objMapper.readValue(inputStream,typeReference);
		} catch (IOException e){
			System.out.println("Unable to find data: " + e.getMessage());
		}
		
		return  dtos;
	}
	
	
	public List<Country> save( List<Country> countries ) {
		return countryService.save(countries);
	}
}
