package com.example.demoapi.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demoapi.dto.country.CountryDTO;
import com.example.demoapi.dto.state.StateDTO;
import com.example.demoapi.exception.BusinessException;
import com.example.demoapi.exception.CountryNotFoundException;
import com.example.demoapi.model.Country;
import com.example.demoapi.model.State;
import com.example.demoapi.service.CountryService;
import com.example.demoapi.service.StateService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("countries")
@AllArgsConstructor
public class CountryController {

	private CountryService service;
	private StateService stateService;
	
	private ModelMapper mapper;
	
	/**
	 * Method that find all the countries
	 */
	@GetMapping
	public ResponseEntity<List<CountryDTO>> findAll() {
		var dtos = service.findAll().stream()
				.map(item -> mapper.map(item, CountryDTO.class))
				.collect(Collectors.toList());
		
		return ResponseEntity.ok( dtos );
	}
	
	
	@GetMapping("/{id}/states")
	public ResponseEntity<List<StateDTO>> findStates(@PathVariable("id") Long id ) {
		List<State> states = stateService.findByCountryId(id);
		List<StateDTO> dtos = states.stream()
				.map(item -> mapper.map(item, StateDTO.class))
				.collect(Collectors.toList());
		return ResponseEntity.ok(dtos);
	}
	
	/**
	 * Method that create a new country
	 */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CountryDTO save( @RequestBody @Valid CountryDTO dto ) {
		var country = mapper.map(dto, Country.class);
		country = service.save(country);
		return mapper.map(country, CountryDTO.class);
	}
	
	
	/**
	 * Method that update a country
	 */
	@PutMapping
	public CountryDTO update( @RequestBody CountryDTO dto ) {
		try {
			var countryUpdate = service.findById(dto.getId()).orElseThrow( () -> new CountryNotFoundException(dto.getId()) );
			mapper.map(dto, countryUpdate);
			countryUpdate = service.save(countryUpdate);
			return mapper.map(countryUpdate, CountryDTO.class);
		} catch (CountryNotFoundException e) {
			throw new BusinessException(e.getMessage(), e);
		}
	}
	
		
	/**
	 * Method that delete a Country by UUID
	 * @param uuid
	 */
	@DeleteMapping("/{uuid}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("uuid") String uuid) {
		service.deleteByCode(uuid);
	}
	
	
	@GetMapping("/local-json")
	public ResponseEntity<List<CountryDTO>> dataLoad() {
		TypeReference<List<CountryDTO>> typeReference = new TypeReference<List<CountryDTO>>(){};
		InputStream inputStream = TypeReference.class.getResourceAsStream("/json/countries.json");
		
		try {
			ObjectMapper objMapper = new ObjectMapper();
			objMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			List<CountryDTO> dtos = objMapper.readValue(inputStream,typeReference);
			return  ResponseEntity.ok(dtos);
		} catch (IOException e){
			System.out.println("Unable to save users: " + e.getMessage());
			return null;
		}
	}
	
	@PostMapping("/local-json")
	public ResponseEntity<List<CountryDTO>> saveDataFromJson() {
		TypeReference<List<CountryDTO>> typeReference = new TypeReference<List<CountryDTO>>(){};
		InputStream inputStream = TypeReference.class.getResourceAsStream("/json/countries.json");
		
		try {
			ObjectMapper objMapper = new ObjectMapper();
			objMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			
			List<CountryDTO> dtos = objMapper.readValue(inputStream,typeReference);
			
			List<Country> countries = dtos.stream()
					.map(item -> mapper.map(item, Country.class))
					.collect(Collectors.toList());
			dtos = service.save(countries).stream()
					.map(item -> mapper.map(item, CountryDTO.class))
					.collect(Collectors.toList());
			
			return  ResponseEntity.ok(dtos);
		} catch (IOException e){
			System.out.println("Unable to save users: " + e.getMessage());
			return null;
		}
	}
}
