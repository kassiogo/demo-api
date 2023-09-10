package com.example.demoapi.controller;

import java.util.List;

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
import com.example.demoapi.dto.state.StateDTO;
import com.example.demoapi.model.State;
import com.example.demoapi.service.CityService;
import com.example.demoapi.service.StateService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("states")
@AllArgsConstructor
public class StateController {

    private StateService service;
    private CityService cityService;
    
    private ModelMapper mapper;

    
    @GetMapping
    public ResponseEntity<List<StateDTO>> findAll() {
        var states = service.findAll().stream()
                .map(item -> mapper.map(item, StateDTO.class))
                .toList();
        return ResponseEntity.ok(states);
    }
    
    
    @GetMapping("/{id}/cities")
    public ResponseEntity<List<CityDTO>> findCities( @PathVariable("id") Long id ) {
        var cities = cityService.findByStateId(id).stream()
                .map(item -> mapper.map(item, CityDTO.class))
                .toList();
        return ResponseEntity.ok(cities);
    }
    
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StateDTO save(@RequestBody StateDTO dto) {
        var state = mapper.map(dto, State.class);
        state = service.save(state);
        return mapper.map(state, StateDTO.class);
    }
    
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete( @PathVariable("id") Long id ) {
    	service.delete(id);
    }
    
}
