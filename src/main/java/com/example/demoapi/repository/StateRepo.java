package com.example.demoapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demoapi.model.State;

public interface StateRepo extends JpaRepository<State, Long> {

	List<State> findByCountryId(Long countryId);
}
