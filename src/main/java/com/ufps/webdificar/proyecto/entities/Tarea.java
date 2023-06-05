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
@Data
@AllArgsConstructor
@NoArgsConstructor
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

}
