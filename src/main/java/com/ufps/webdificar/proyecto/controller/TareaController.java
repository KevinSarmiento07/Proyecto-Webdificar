package com.ufps.webdificar.proyecto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ufps.webdificar.proyecto.entities.Proyecto;
import com.ufps.webdificar.proyecto.entities.Tarea;
import com.ufps.webdificar.proyecto.entities.Trabajador;
import com.ufps.webdificar.proyecto.repositories.ProyectoRepository;
import com.ufps.webdificar.proyecto.repositories.TareaRepository;
import com.ufps.webdificar.proyecto.repositories.TrabajadorRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/tarea")
public class TareaController {

	@Autowired
	private TareaRepository tareaRepository;
	
	@Autowired
	private ProyectoRepository proyectoRepository;

	@Autowired
	private TrabajadorRepository trabajadorRepository;

	@GetMapping("/registrar")
	public String registro(@Valid Tarea tarea, Model model, BindingResult result) {
		List<Trabajador> trabajadores = trabajadorRepository.findAll();
		List<Proyecto> proyectos = proyectoRepository.findAll();
		model.addAttribute("trabajadores", trabajadores);
		model.addAttribute("proyectos", proyectos);
		return "views/tareaRegistrar";
	}
	
	@GetMapping("/listar")
	public String listarTareas(Model model) {
		
		List<Tarea> tareas = tareaRepository.findAll();
		model.addAttribute("tareas", tareas);
		return "views/proyectoEdit";// clocar vistar
	}
	
	@Transactional
	@PostMapping("/crear")
	public String procesarFormCrearTarea(@Valid Tarea tarea, Model model){
		Tarea tareaNueva = tareaRepository.save(tarea);		
		model.addAttribute("tarea", tareaNueva);
		return "redirect:/proyecto/editar/"+tareaNueva.getProyecto().getId().toString();
	}
	
	@GetMapping("/listar/tareas")
	public String crearTarea(Model model) {
		List<Tarea> tareas = tareaRepository.findAll();
		model.addAttribute("tareas", tareas);
		return "views/tareaListar"; 
	}
	
	
	@GetMapping("/editar/{id}")
	public String editarTarea(@Valid Tarea tarea, Model model) {
		Tarea tareaEditda = tareaRepository.findById(tarea.getId()).orElse(null);
		model.addAttribute("tarea", tareaEditda);
		return "";
	}
	
	@Transactional
	@PostMapping("/editar/{id}")
	public String procesarEditarTarea(@PathVariable Integer id, Tarea tarea) {
		tarea.setId(id);
		tareaRepository.save(tarea);
		return "redirect:/tarea/listar/tareas";//redirect
	}
	
	
	@Transactional
	@PostMapping("/eliminar/{id}")
	public String eliminarTarea(@PathVariable Integer id) {
		tareaRepository.deleteById(id);
		return "redirect:/tarea";
	}
	
	
	
}
