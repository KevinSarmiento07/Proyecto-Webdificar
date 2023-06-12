package com.ufps.webdificar.proyecto.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ufps.webdificar.proyecto.entities.Trabajador;
import com.ufps.webdificar.proyecto.repositories.TrabajadorRepository;

@Controller
@RequestMapping("/trabajador")
public class TrabajadorController {

	@Autowired
	TrabajadorRepository trabajadorRepository;
	
	@GetMapping
	public String listarTrabajadores(Model model) {
		List<Trabajador> trabajadores = trabajadorRepository.findAll();
		model.addAttribute("trabajadores", trabajadores);
		return "";
	}
	
	@GetMapping("/editar/{id}")
	public String editarTrabajador(Trabajador trabajador, Model model) {
		Trabajador trabajadorEditado = trabajadorRepository.findById(trabajador.getId()).orElse(null);
		model.addAttribute("trabajador", trabajadorEditado);
		
		return "";
	}
	
	@PutMapping("/editar/{id}")
	public String procesarEditarTrabajador(@PathVariable Integer id, Trabajador trabajador) {
		trabajador.setId(id);
		trabajadorRepository.save(trabajador);
		return "";
	}
}
