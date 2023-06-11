package com.ufps.webdificar.proyecto.service.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufps.webdificar.proyecto.entities.Proyecto;
import com.ufps.webdificar.proyecto.repositories.ProyectoRepository;
import com.ufps.webdificar.proyecto.service.interfaces.ProyectoInterface;

@Service
public class ProyectoServiceImple implements ProyectoInterface{

	@Autowired
	ProyectoRepository proyectoRepository;
	
	@Override
	public List<Proyecto> getAll() {
		return proyectoRepository.findAll();
	}

	@Override
	public Proyecto getById(Proyecto proyecto) {
		return proyectoRepository.findById(proyecto.getId()).orElse(null);
	}

	@Override
	public Proyecto create(Proyecto proyecto) {
		return proyectoRepository.save(proyecto);
	}

	@Override
	public Proyecto update(Proyecto proyecto) {
		return proyectoRepository.save(proyecto);
	}

	@Override
	public void deleteById(Integer id) {
		proyectoRepository.deleteById(id);
	}

}
