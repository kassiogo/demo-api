package com.example.demoapi.dto.country;

import com.example.demoapi.dto.BaseDTO;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class CountryJsonDTO extends BaseDTO {

	private String name;
	private String iso3;
	private String iso2;
	
}
