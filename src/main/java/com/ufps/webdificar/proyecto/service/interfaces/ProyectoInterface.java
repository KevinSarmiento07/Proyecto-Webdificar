package com.ufps.webdificar.proyecto.service.interfaces;

import java.util.List;

import com.ufps.webdificar.proyecto.entities.Proyecto;

public interface ProyectoInterface {
	
	public List<Proyecto> getAll();
	
	public Proyecto getById(Proyecto proyecto);
	
	public Proyecto create(Proyecto proyecto);
	
	
	public Proyecto update(Proyecto proyecto);
	
	public void deleteById(Integer id);

}
