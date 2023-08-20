package com.example.demoapi.dto.country;

import com.example.demoapi.dto.BaseDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CountryDTO extends BaseDTO {

	private String name;
	private String initials;
	
}
