package com.ufps.webdificar.proyecto.controller;

import java.util.List;

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

import com.ufps.webdificar.proyecto.entities.Documento;
import com.ufps.webdificar.proyecto.repositories.DocumentoRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/documento")
public class DocumentoController {

	@Autowired
	DocumentoRepository documentoRepository;

	@GetMapping
	public String listarDocumentos(Model model) {
		List<Documento> documentos = documentoRepository.findAll();
		model.addAttribute("documentos", documentos);
		return "views/documentoListar";
	}
	
	@GetMapping("/crear")
	public String crearDocumento(@Valid Documento documento) {
		return "views/documentoCrear";
	}
	
	@PostMapping("/crear")
	public String procesarCrearDocumento(@Valid Documento documento, BindingResult result, Model model) {
		Documento documentoCreado = documentoRepository.save(documento);
		model.addAttribute("documento", documentoCreado);
		return "redirect:/documento";
	}
	
	@GetMapping("/editar/{id}")
	public String editarDocumentoVista(@Valid Documento documento , Model model) {
		Documento documentoEditado = documentoRepository.findById(documento.getId()).orElse(null);
		model.addAttribute("documento", documentoEditado);
		return "views/documentoEditar"; 
	}
	
	
	@PutMapping("/editar/{id}")
	public String editarDocumento(@Valid Documento documento, BindingResult result, Model model) {
		Documento documentoEditado = documentoRepository.findById(documento.getId()).orElse(null);
		model.addAttribute("documento", documentoEditado);
		return "redirect:/documento";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminarDocumento(@PathVariable("id") Integer id) {
		documentoRepository.deleteById(id);
		return "redirect:/documento";
	}
}
