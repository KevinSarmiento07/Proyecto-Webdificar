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
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
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
	
	

	public Trabajador() {
	}

	public Trabajador(Integer id, @NotEmpty String nombre, @NotEmpty String contrasena, @NotEmpty String documento,
			@Email @NotEmpty String correo, Integer rol, Integer permiso_documento, Integer permitir_seguimiento,
			Set<Proyecto> proyectos, Set<Tarea> tareasAsignadas, Set<Tarea> tareasEncargadas,
			List<Documento> documentos) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.contrasena = contrasena;
		this.documento = documento;
		this.correo = correo;
		this.rol = rol;
		this.permiso_documento = permiso_documento;
		this.permitir_seguimiento = permitir_seguimiento;
		this.proyectos = proyectos;
		this.tareasAsignadas = tareasAsignadas;
		this.tareasEncargadas = tareasEncargadas;
		this.documentos = documentos;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Integer getRol() {
		return rol;
	}

	public void setRol(Integer rol) {
		this.rol = rol;
	}

	public Integer getPermiso_documento() {
		return permiso_documento;
	}

	public void setPermiso_documento(Integer permiso_documento) {
		this.permiso_documento = permiso_documento;
	}

	public Integer getPermitir_seguimiento() {
		return permitir_seguimiento;
	}

	public void setPermitir_seguimiento(Integer permitir_seguimiento) {
		this.permitir_seguimiento = permitir_seguimiento;
	}

	public Set<Proyecto> getProyectos() {
		return proyectos;
	}

	public void setProyectos(Set<Proyecto> proyectos) {
		this.proyectos = proyectos;
	}

	public Set<Tarea> getTareasAsignadas() {
		return tareasAsignadas;
	}

	public void setTareasAsignadas(Set<Tarea> tareasAsignadas) {
		this.tareasAsignadas = tareasAsignadas;
	}

	public Set<Tarea> getTareasEncargadas() {
		return tareasEncargadas;
	}

	public void setTareasEncargadas(Set<Tarea> tareasEncargadas) {
		this.tareasEncargadas = tareasEncargadas;
	}

	public List<Documento> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(List<Documento> documentos) {
		this.documentos = documentos;
	}
	
	

}
