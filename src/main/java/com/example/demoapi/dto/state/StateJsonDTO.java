package com.example.demoapi.dto.state;

import com.example.demoapi.dto.BaseDTO;
import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class StateJsonDTO extends BaseDTO {

	private String name;
	@JsonAlias("state_code")
	private String stateCode;
	@JsonAlias("country_id")
	private Long countryId;
	
}
