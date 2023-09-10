package com.example.demoapi.dto.state;

import com.example.demoapi.dto.BaseDTO;
import com.example.demoapi.dto.country.CountryDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class StateDTO extends BaseDTO {
    private String name;
    private String stateCode;
    private CountryDTO country;
}
