package com.ufps.webdificar.proyecto.controller;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ufps.webdificar.proyecto.entities.Proyecto;
import com.ufps.webdificar.proyecto.entities.Trabajador;
import com.ufps.webdificar.proyecto.repositories.ProyectoRepository;

@Controller
@RequestMapping("/proyecto")
public class ProyectoController {
	
	@Autowired
	private ProyectoRepository proyectoRepository;
	
	
	@GetMapping
	public String vistaProyecto(Model model, Trabajador trabajador) {
		Set<Proyecto> proyectos = trabajador.getProyectos();
		model.addAttribute("proyectos", proyectos);
		return ""; //Redireccionar a la vista del listado de proyectos
	}
	
	@PostMapping
	public String crearProyecto(@RequestParam("nombre") String nombre, @RequestParam("encargado") String encargado, @RequestParam("fechaInicio") Date fechaInicio,
			@RequestParam("fechaFin") Date fechaFin, @RequestParam("descripcion") String descripcion) {
		Proyecto proyecto = new Proyecto(nombre, encargado, fechaInicio, fechaFin, descripcion);
		proyectoRepository.save(proyecto);
		return ""; //redireccionar a alguna vista despues de crear el proyecto
	}

	
	
}
