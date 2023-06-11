package com.ufps.webdificar.proyecto.service.interfaces;

import java.util.List;

import com.ufps.webdificar.proyecto.entities.Tarea;

public interface TareaInterface {

	
	public List<Tarea> getAll();
	
	public Tarea getById(Tarea tarea);
	
	public Tarea create(Tarea tarea);
	
	
	public Tarea update(Tarea tarea);
	
	public void deleteById(Integer id);
}
