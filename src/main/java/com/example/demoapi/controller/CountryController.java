package com.example.demoapi.controller;

import java.util.List;

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
import com.example.demoapi.exception.BusinessException;
import com.example.demoapi.exception.CountryNotFoundException;
import com.example.demoapi.model.Country;
import com.example.demoapi.service.CountryService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("countries")
@AllArgsConstructor
public class CountryController {

	private CountryService service;
	private ModelMapper mapper;
	
	/**
	 * Method that find all the countries
	 */
	@GetMapping
	public ResponseEntity<List<CountryDTO>> findAll() {
		var dtos = service.findAll().stream()
				.map(item -> mapper.map(item, CountryDTO.class))
				.toList();
		
		return ResponseEntity.ok( dtos );
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
}
