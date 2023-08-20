package com.example.demoapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class City extends BaseEntity {

	@Column(nullable = false, length = 250)
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "state_id", nullable = false, foreignKey = @ForeignKey(name = "FK_CityState"))
	private State state;
}
