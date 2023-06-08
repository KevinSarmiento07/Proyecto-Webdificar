package com.ufps.webdificar.proyecto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/nav")
public class NavController {

	
	@GetMapping("/acerca")
	public String retornarAcerca() {
		
		return "views/acercade";
	}
	
	@GetMapping("/servicio")
	public String retornarServicios() {
		
		return "views/servicio";
	}
	
	@GetMapping("/contacto")
	public String retornarContacto() {
		
		return "views/contacto";
	}
}
