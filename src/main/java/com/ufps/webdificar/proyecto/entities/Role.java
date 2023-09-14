package com.ufps.webdificar.proyecto.entities;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;

@Table(name = "authorities", uniqueConstraints = {@UniqueConstraint(columnNames = {"trabajador_id", "authority"})})
@Entity
@Data
public class Role implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String authority;
	
	
	
}
