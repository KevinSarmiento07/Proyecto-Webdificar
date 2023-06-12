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
import com.ufps.webdificar.proyecto.entities.Tarea;
import com.ufps.webdificar.proyecto.entities.Trabajador;
import com.ufps.webdificar.proyecto.repositories.ProyectoRepository;
import com.ufps.webdificar.proyecto.repositories.TareaRepository;

@Controller
@RequestMapping("/proyecto")
public class ProyectoController {
	
	@Autowired
	private ProyectoRepository proyectoRepository;
	
	@Autowired
	private TareaRepository tareaRepository;
	
	@GetMapping
	public String vistaProyecto(Model model) {
		List<Tarea> tareas = tareaRepository.findAll();
		model.addAttribute("tareas", tareas);
		return "views/proyectos"; //Redireccionar a la vista del listado de proyectos
	}
	
	
	
	@GetMapping("/crear")
	public String visyaFormProyecto() {
		return "views/proyectoRegistrar";
	}
	
	
	
	@PostMapping("/crear")
	public String crearProyecto(@RequestParam("nombre") String nombre, @RequestParam("encargado") String encargado, @RequestParam("fechaInicio") Date fechaInicio,
			@RequestParam("fechaFin") Date fechaFin, @RequestParam("descripcion") String descripcion) {
		Proyecto proyecto = new Proyecto(null, nombre, encargado, fechaInicio, fechaFin, descripcion, null, null);
		proyectoRepository.save(proyecto);
		return "redirect:/proyecto/listar"; //redireccionar a alguna vista despues de crear el proyecto
	}
	
	
	@GetMapping("/listar")
	public String listarProyectos() {
		return "views/proyectoListar";
	}
	
	

	
	
}
