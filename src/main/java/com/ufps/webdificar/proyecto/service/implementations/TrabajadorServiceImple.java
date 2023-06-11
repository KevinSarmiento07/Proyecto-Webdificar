package com.ufps.webdificar.proyecto.service.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufps.webdificar.proyecto.entities.Trabajador;
import com.ufps.webdificar.proyecto.repositories.TrabajadorRepository;
import com.ufps.webdificar.proyecto.service.interfaces.TrabajadorInterface;

@Service
public class TrabajadorServiceImple implements TrabajadorInterface {

	@Autowired
	TrabajadorRepository trabajadorRepository;
	
	@Override
	public List<Trabajador> getAll() {
		return trabajadorRepository.findAll();
	}

	@Override
	public Trabajador getById(Trabajador trabajador) {
		return trabajadorRepository.findById(trabajador.getId()).orElse(null);
	}

	@Override
	public Trabajador create(Trabajador trabajador) {
		return trabajadorRepository.save(trabajador);
	}

	@Override
	public Trabajador update(Trabajador trabajador) {
		return trabajadorRepository.save(trabajador);
	}

	@Override
	public void deleteById(Integer id) {
		trabajadorRepository.deleteById(id);
	}

	@Override
	public Trabajador getByCorreo(String correo) {
		return trabajadorRepository.findByCorreo(correo);
	}

	
}
