package com.ufps.webdificar.proyecto.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ufps.webdificar.proyecto.entities.Documento;
import com.ufps.webdificar.proyecto.entities.Proyecto;
import com.ufps.webdificar.proyecto.entities.Tarea;
import com.ufps.webdificar.proyecto.entities.Trabajador;
import com.ufps.webdificar.proyecto.repositories.DocumentoRepository;
import com.ufps.webdificar.proyecto.repositories.ProyectoRepository;
import com.ufps.webdificar.proyecto.repositories.TareaRepository;
import com.ufps.webdificar.proyecto.repositories.TrabajadorRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/proyecto")
public class ProyectoController {
	
	@Autowired
	private ProyectoRepository proyectoRepository;
	
	@Autowired
	private TareaRepository tareaRepository;
	
	@Autowired
	private DocumentoRepository documentoRepository;

	@Autowired
	private TrabajadorRepository trabajadorRepository;
	
	@GetMapping("/listar")
	public String listarProyectos(Model model) {
		List<Proyecto> proyectos = proyectoRepository.findAll();
		model.addAttribute("proyectos", proyectos);
		return "views/proyectos"; //Redireccionar a la vista del listado de proyectos
	}
	
	
	
	@GetMapping("/crear")
	public String visyaFormProyecto(Proyecto proyecto, Model model, BindingResult result) {
		List<Trabajador> trabajadores = trabajadorRepository.findAll();
		model.addAttribute("trabajadores", trabajadores);
		return "views/proyectoRegistrar";
	}
	
	
	
	@PostMapping("/crear")
	public String crearProyecto(@Valid Proyecto proyecto, Model model, BindingResult result) {
		Proyecto nuevoProyecto = proyectoRepository.save(proyecto);
		model.addAttribute("proyecto", nuevoProyecto);
		return "redirect:/proyecto/listar"; //redireccionar a alguna vista despues de crear el proyecto
	}
	
	
	@GetMapping("/editar/{id}")
	public String editarProyecto(@Valid Proyecto proyecto, Model model, BindingResult result) {
		
		Proyecto proyectoEditar = proyectoRepository.findById(proyecto.getId()).orElse(null);
		List<Documento> documentos = documentoRepository.findAll();
		List<Trabajador> trabajadores = trabajadorRepository.findAll();
		List<Tarea> tareas = new ArrayList<>();
		
		for(Tarea tarea : tareaRepository.findAll()) {
			if(proyectoEditar.getId() == tarea.getProyecto().getId()) {
				tareas.add(tarea);
			}
		}
		
		model.addAttribute("documentos", documentos);
		model.addAttribute("proyecto", proyectoEditar);
		model.addAttribute("trabajadores", trabajadores);
		model.addAttribute("tareas", tareas);
		return "views/proyectoEdit"; //redireccionar a editar proyectos
	}
	
	
	@PutMapping("/editar/{id}")
	public String procesarEditarProyecto(@Valid Proyecto proyecto, Model model, @PathVariable("id") Integer id) {
		Proyecto proyectoEditado = proyectoRepository.save(proyecto);
		model.addAttribute("proyecto", proyectoEditado);
		return "views/proyectoEdit";
	}
	
	
	@DeleteMapping("/eliminar/{id}")
	public String eliminarProyecto(@PathVariable Integer id) {
		proyectoRepository.deleteById(id);
		return "redirect:/proyecto";
	}
	
	
	
	
	

	
	
}
