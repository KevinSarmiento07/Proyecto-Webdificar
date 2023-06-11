package com.ufps.webdificar.proyecto.service.interfaces;

import java.util.List;

import com.ufps.webdificar.proyecto.entities.Trabajador;

public interface TrabajadorInterface {

	
	public List<Trabajador> getAll();
	
	public Trabajador getById(Trabajador trabajador);
	
	public Trabajador create(Trabajador trabajador);
	
	
	public Trabajador update(Trabajador trabajador);
	
	public void deleteById(Integer id);
	
	public Trabajador getByCorreo(String correo);
}
