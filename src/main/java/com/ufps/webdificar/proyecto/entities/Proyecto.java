package com.ufps.webdificar.proyecto.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "proyecto")
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class Proyecto {

	


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nombre;
	private String encargado;
	
	@Column(name = "fecha_inicio")
	private Date fechaInicio;
	
	@Column(name = "fecha_fin")
	private Date fechaFin;
	
	
	private String descripcion;
	
	
	
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "proyectos_trabajadores",
            joinColumns = @JoinColumn(name = "trabajador_id"),
            inverseJoinColumns = @JoinColumn(name = "proyecto_id"))
    private Set<Proyecto> proyectos = new HashSet<>();
	
	
	 @OneToMany(mappedBy = "proyecto", cascade = CascadeType.ALL, orphanRemoval = true)
	    private Set<Tarea> tareas = new HashSet<>();

	 public Proyecto(String nombre, String encargado, Date fechaInicio, Date fechaFin, String descripcion) {
			this.nombre = nombre;
			this.encargado = encargado;
			this.fechaInicio = fechaInicio;
			this.fechaFin = fechaFin;
			this.descripcion = descripcion;
		}

	 
	 
	public Proyecto() {
	}



	public Proyecto(Integer id, String nombre, String encargado, Date fechaInicio, Date fechaFin, String descripcion,
			Set<Proyecto> proyectos, Set<Tarea> tareas) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.encargado = encargado;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.descripcion = descripcion;
		this.proyectos = proyectos;
		this.tareas = tareas;
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

	public String getEncargado() {
		return encargado;
	}

	public void setEncargado(String encargado) {
		this.encargado = encargado;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Set<Proyecto> getProyectos() {
		return proyectos;
	}

	public void setProyectos(Set<Proyecto> proyectos) {
		this.proyectos = proyectos;
	}

	public Set<Tarea> getTareas() {
		return tareas;
	}

	public void setTareas(Set<Tarea> tareas) {
		this.tareas = tareas;
	}
	 
	 

}
