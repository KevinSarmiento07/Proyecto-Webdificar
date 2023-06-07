package com.ufps.webdificar.proyecto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ufps.webdificar.proyecto.entities.Proyecto;
import com.ufps.webdificar.proyecto.entities.Tarea;
import com.ufps.webdificar.proyecto.repositories.ProyectoRepository;
import com.ufps.webdificar.proyecto.repositories.TareaRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/tarea")
public class TareaController {

	@Autowired
	private TareaRepository tareaRepository;
	
	@Autowired
	private ProyectoRepository proyectoRepository;
	
	@GetMapping
	public String listarTareas(Model model) {
		
		List<Tarea> tareas = tareaRepository.findAll();
		model.addAttribute("tareas", tareas);
		return "";// clocar vistar
	}
	
	@GetMapping("/crear")
	public String crearTarea(Model model) {
		List<Proyecto> proyectos = proyectoRepository.findAll();
		model.addAttribute("proyectos", proyectos);
		return ""; 
	}
	
	
	@PostMapping 
	public String procesarFormCrearTarea(@Valid Tarea tarea, Model model){
		Tarea tareaNueva = tareaRepository.save(tarea);
		model.addAttribute("tarea", tareaNueva);
		return "redirect:/tarea";
	}
	
}
