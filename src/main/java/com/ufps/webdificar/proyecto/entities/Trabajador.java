package com.ufps.webdificar.proyecto.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.persistence.JoinColumn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "trabajador", uniqueConstraints = @UniqueConstraint(columnNames = "correo"))
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Trabajador {

	public Trabajador(String nombre, String contrasena, String documento, String correo) {
		this.nombre = nombre;
		this.contrasena = contrasena;
		this.documento = documento;
		this.correo = correo;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotEmpty
	private String nombre;
	@NotEmpty
	private String contrasena;

	@NotEmpty
	private String documento;
	
	@Email
	@NotEmpty
	private String correo;
	private Integer rol;
	private Integer permiso_documento;
	private Integer permitir_seguimiento;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "proyectos_trabajadores", joinColumns = @JoinColumn(name = "trabajador_id"), inverseJoinColumns = @JoinColumn(name = "proyecto_id"))
	private Set<Proyecto> proyectos = new HashSet<>();

	@OneToMany(mappedBy = "trabajadorAsignado", cascade = CascadeType.ALL)
	private Set<Tarea> tareasAsignadas = new HashSet<>();

	@OneToMany(mappedBy = "trabajadorEncargado", cascade = CascadeType.ALL)
	private Set<Tarea> tareasEncargadas = new HashSet<>();

	@OneToMany(mappedBy = "trabajador", cascade = CascadeType.ALL)
	private List<Documento> documentos = new ArrayList<>();

}
