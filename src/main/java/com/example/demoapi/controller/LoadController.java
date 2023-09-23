package com.example.demoapi.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demoapi.dto.country.CountryDTO;
import com.example.demoapi.dto.load.JsonCityDTO;
import com.example.demoapi.dto.load.JsonCountryDTO;
import com.example.demoapi.dto.load.JsonStateDTO;
import com.example.demoapi.model.City;
import com.example.demoapi.model.Country;
import com.example.demoapi.model.State;
import com.example.demoapi.service.CityService;
import com.example.demoapi.service.CountryService;
import com.example.demoapi.service.LoadService;
import com.example.demoapi.service.StateService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("loads")
@AllArgsConstructor
public class LoadController {

	private LoadService service;
	private CountryService countryService;
	private StateService stateService;
	private CityService cityService;
	
	private ModelMapper mapper;
	
	@GetMapping
	public ResponseEntity<List<JsonCountryDTO>> dataLoad() {
		return ResponseEntity.ok( service.findAll() );
	}
	
	
	@PostMapping
	public ResponseEntity<List<CountryDTO>> saveDataFromJson() {
		List<JsonCountryDTO> jsonResources = service.findAll();
		List<Country> countries = new ArrayList<>();
		
		int i = 0;
		for ( JsonCountryDTO jsonCountry : jsonResources ) {
			if ( jsonCountry.getStates().isEmpty() ) {
				continue;
			}
			
			Country country = mapper.map(jsonCountry, Country.class);
			country = countryService.save(country);
			
			for (JsonStateDTO jsonState : jsonCountry.getStates()) {
				State state = mapper.map(jsonState, State.class);
				state.setCountry(country);
				state = stateService.save(state);
				for (JsonCityDTO jsonCity : jsonState.getCities()) {
					City city = mapper.map(jsonCity, City.class);
					city.setState(state);
					cityService.save(city);
				}
			}
			
			countries.add(country);
			
			i = i + 1;
			if (i == 5) {
				break;
			}
		}
		
		List<CountryDTO> dtosSaved = countries.stream()
			.map(item -> mapper.map(item, CountryDTO.class))
				.collect(Collectors.toList());
		
		return ResponseEntity.ok(dtosSaved);
	}
	
}
