package com.ufps.webdificar.proyecto.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tarea")
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class Tarea {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nombre;

	private String descripcion;

	@Column(name = "tipo_tarea")
	private Integer tipoTarea;

	private Date fecha;

	@Column(name = "fecha_inicio")
	private Date fechaInicio;

	@Column(name = "fecha_fin")
	private Date fechaFin;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "proyecto_id")
	private Proyecto proyecto;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "trabajador_asignado_id")
	private Trabajador trabajadorAsignado;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "trabajador_encargado_id")
	private Trabajador trabajadorEncargado;

	@OneToMany(mappedBy = "tarea", cascade = CascadeType.ALL)
	private List<Documento> documentos = new ArrayList<>();

	
	
	public Tarea() {
	}

	public Tarea(Integer id, String nombre, String descripcion, Integer tipoTarea, Date fecha, Date fechaInicio,
			Date fechaFin, Proyecto proyecto, Trabajador trabajadorAsignado, Trabajador trabajadorEncargado,
			List<Documento> documentos) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.tipoTarea = tipoTarea;
		this.fecha = fecha;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.proyecto = proyecto;
		this.trabajadorAsignado = trabajadorAsignado;
		this.trabajadorEncargado = trabajadorEncargado;
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getTipoTarea() {
		return tipoTarea;
	}

	public void setTipoTarea(Integer tipoTarea) {
		this.tipoTarea = tipoTarea;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
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

	public Proyecto getProyecto() {
		return proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}

	public Trabajador getTrabajadorAsignado() {
		return trabajadorAsignado;
	}

	public void setTrabajadorAsignado(Trabajador trabajadorAsignado) {
		this.trabajadorAsignado = trabajadorAsignado;
	}

	public Trabajador getTrabajadorEncargado() {
		return trabajadorEncargado;
	}

	public void setTrabajadorEncargado(Trabajador trabajadorEncargado) {
		this.trabajadorEncargado = trabajadorEncargado;
	}

	public List<Documento> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(List<Documento> documentos) {
		this.documentos = documentos;
	}
	
	
	

}
