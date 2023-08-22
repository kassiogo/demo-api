package com.example.demoapi.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrePersist;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Country extends BaseEntity {

	@Column(nullable = false, length = 250)
	private String name;
	
	@Column(nullable = false, length = 3)
	private String initials;
	
	private String code;
	
	@PrePersist
	private void generateCode() {
		setCode(UUID.randomUUID().toString());
	}
	
}
