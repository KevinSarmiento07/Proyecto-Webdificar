package com.ufps.webdificar.proyecto.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.stereotype.Controller
@RequestMapping("/controlador")
public class Controller {
	
	
	@GetMapping("/vista")
	public String retornarIndex() {
		
		return "index";
	}

}
