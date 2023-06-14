package com.ufps.webdificar.proyecto.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "documento")
public class Documento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nombre;

	private String descripcion;

	@Column(name = "tipo_documento")
	private Integer tipoDocumento;

	private String url;

	private String fecha;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "trabajador_id")
	private Trabajador trabajador;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tarea_id")
	private Tarea tarea;

	@Lob
    private byte[] archivo;
	
	
	
	
}
