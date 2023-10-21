package com.example.demoapi.model;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntityAudit extends BaseEntity {

	private String createdBy;
	private String updatedBy;
	
	@CreationTimestamp
	@Column(updatable = false)
	private LocalDateTime createdAt;
	
	@UpdateTimestamp
	private LocalDateTime updatedAt;

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(createdAt, createdBy, updatedAt, updatedBy);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseEntityAudit other = (BaseEntityAudit) obj;
		return Objects.equals(createdAt, other.createdAt) && Objects.equals(createdBy, other.createdBy)
				&& Objects.equals(updatedAt, other.updatedAt) && Objects.equals(updatedBy, other.updatedBy);
	}

	@Override
	public String toString() {
		return "BaseEntityAudit [createdBy=" + createdBy + ", updatedBy=" + updatedBy + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + "]";
	}
	
}
