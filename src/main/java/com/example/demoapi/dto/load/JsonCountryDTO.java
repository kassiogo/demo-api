package com.example.demoapi.dto.load;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class JsonCountryDTO {

	// private Long id;
	private String name;
	private String iso3;
	private String iso2;
	private List<JsonStateDTO> states = new ArrayList<>(); 
}
