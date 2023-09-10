package com.example.demoapi.dto.load;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.Data;

@Data
public class JsonStateDTO {
	// private Long id;
	private String name;
	@JsonAlias("state_code")
	private String stateCode;
	private List<JsonCityDTO> cities = new ArrayList<>();
}
