package com.example.demoapi.dto.city;

import com.example.demoapi.dto.BaseDTO;
import com.example.demoapi.dto.state.StateDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CityDTO extends BaseDTO {

	private String name;
	private StateDTO state;
}
