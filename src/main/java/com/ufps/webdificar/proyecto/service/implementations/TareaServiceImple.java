package com.ufps.webdificar.proyecto.service.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufps.webdificar.proyecto.entities.Tarea;
import com.ufps.webdificar.proyecto.repositories.TareaRepository;
import com.ufps.webdificar.proyecto.service.interfaces.TareaInterface;

@Service
public class TareaServiceImple implements TareaInterface {

	@Autowired
	TareaRepository tareaRepository;
	
	@Override
	public List<Tarea> getAll() {
		return tareaRepository.findAll();
	}

	@Override
	public Tarea getById(Tarea tarea) {
		return tareaRepository.findById(tarea.getId()).orElse(null);
	}

	@Override
	public Tarea create(Tarea tarea) {
		return tareaRepository.save(tarea);
	}

	@Override
	public Tarea update(Tarea tarea) {
		return tareaRepository.save(tarea);
	}

	@Override
	public void deleteById(Integer id) {
		tareaRepository.deleteById(id);
	}

}
