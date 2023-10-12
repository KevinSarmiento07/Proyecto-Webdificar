package com.ufps.webdificar.proyecto.controller;

import java.util.List;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
import com.ufps.webdificar.proyecto.service.implementations.JpaUserDetailsService;

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
	
	@Autowired
	private JpaUserDetailsService userDetailService;

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
		model.addAttribute("trabajador", getTrabajador());
		model.addAttribute("tareas", tareas);
		return "views/tareaListar"; 
	}
	
	
	@GetMapping("/editar/{id}")
	public String editarTarea(@Valid Tarea tarea, Model model) {
		Tarea tareaEditda = tareaRepository.findById(tarea.getId()).orElse(null);
		
		List<Proyecto> proyectos = proyectoRepository.findAll();
		List<Trabajador> trabajadores = trabajadorRepository.findAll();
		model.addAttribute("trabajador", getTrabajador());
		model.addAttribute("tarea", tareaEditda);
		model.addAttribute("proyectos", proyectos);
		model.addAttribute("trabajadores", trabajadores);
		return "views/tareaEditar";
	}
	
	@Transactional
	@PostMapping("/editar/{id}")
	public String procesarEditarTarea(@PathVariable Integer id, Tarea tarea) {
		tarea.setId(id);
		tareaRepository.save(tarea);
		return "redirect:/tarea/listar/tareas";//redirect
	}
	
	
	@Transactional
	@GetMapping("/eliminar/{id}")
	public String eliminarTarea(@PathVariable Integer id) {
		tareaRepository.deleteById(id);
		return "redirect:/tarea/listar/tareas";
	}
	
	private Trabajador getTrabajador() {
		org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return userDetailService.findByUsername(auth.getName());
	}
	
}
