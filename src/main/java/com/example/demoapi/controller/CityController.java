package com.example.demoapi.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demoapi.dto.city.CityDTO;
import com.example.demoapi.dto.city.CityPlanDTO;
import com.example.demoapi.model.City;
import com.example.demoapi.service.CityService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("cities")
@AllArgsConstructor
public class CityController {

	private CityService service;
	private ModelMapper mapper;

	/**
	 * Method that find all the cities
	 */
	@GetMapping
	public ResponseEntity<List<CityPlanDTO>> findAll() {
		var cities = service.findAll()
				.stream()
				.map(item -> mapper.map(item, CityPlanDTO.class))
				.collect(Collectors.toList());
		return ResponseEntity.ok(cities);
	}
	

	/**
	 * Method that create a new city
	 */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CityDTO create( @RequestBody CityDTO dto  ) {
		var city = mapper.map(dto, City.class);
		city = service.save(city);
		return mapper.map(city, CityDTO.class);
	}


	/**
	 * Method that delete a city
	 */
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Long id) {
		service.delete(id);
	}
	
}
