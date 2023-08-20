package com.example.demoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demoapi.model.State;

public interface StateRepo extends JpaRepository<State, Long> {

}
