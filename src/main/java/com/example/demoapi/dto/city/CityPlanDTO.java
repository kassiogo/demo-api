package com.example.demoapi.dto.city;

import com.example.demoapi.dto.BaseDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CityPlanDTO extends BaseDTO {

	private String name;
	private String stateAbbreviation;
	
}
