package com.ufps.webdificar.proyecto.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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
@Table(name = "trabajador")
@Data
@AllArgsConstructor
public class Trabajador implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Trabajador() {
		this.roles = new ArrayList<Role>();
	}
	
	public Trabajador(String nombre,String contrasena,String documento,String username) {
		this.nombre = nombre;
		this.password = contrasena;
		this.documento = documento;
		this.username = username;
		this.roles = new ArrayList<Role>();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotEmpty
	private String nombre;
	
	
	private boolean enabled;

	@NotEmpty
	private String documento;
	
	@Email
	@NotEmpty
	@Column(length = 255,unique = true)
	private String username;
	
	@Column(length = 60)
	private String password;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "trabajador_id")
	private List<Role> roles;
	
	
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

	@Override
	public String toString() {
		return "Trabajador [id=" + id + ", nombre=" + nombre + ", enabled=" + enabled + ", documento=" + documento
				+ ", username=" + username + ", password=" + password  + "]";
	}

	public void addRole(Role role) {
		// TODO Auto-generated method stub
		this.roles.add(role);
	}

	public boolean hasRole(String rol) {
		// TODO Auto-generated method stub
		return this.roles.get(0).getAuthority().equals(rol);
	}
	
	
	
	
	

}
