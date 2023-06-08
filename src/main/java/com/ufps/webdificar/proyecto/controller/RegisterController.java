package com.ufps.webdificar.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ufps.webdificar.proyecto.entities.Trabajador;
import com.ufps.webdificar.proyecto.repositories.TrabajadorRepository;

@org.springframework.stereotype.Controller
@RequestMapping("/")
public class RegisterController {
	
	@Autowired
	private TrabajadorRepository trabajadorRepository;
	
	@GetMapping
	public String iniciarSesion() {
		return "index";
	}
	
	
	@PostMapping
	public String procesarIniciarSesion(@RequestParam("correo") String correo, @RequestParam("contrasena") String contrasena, Model model) {

		Trabajador trabajador = trabajadorRepository.findByCorreo(correo);
		if( !(trabajador!= null && trabajador.getContrasena().equals(contrasena))) {
			return ""; // aqui debe redireccionar a una pagina donde diga que el usuario o contraseña son incorrectos 
		}
		
		model.addAttribute("trabajador", trabajador);
		return ""; //redireccionar a la vista principal
	}
	
	@GetMapping("/registro")
	public String registro() {
		return "views/register";
	}
	
	
	@PostMapping("/registro")
	public String procesarRegistro(@RequestParam("nombre") 
	String nombre, @RequestParam("contrasena") 
	String contrasena,@RequestParam("documento") 
	String documento,  @RequestParam("correo") 
	String correo) {
		if(trabajadorRepository.findByCorreo(correo) != null) {
			return ""; // aqui debe reddireccionar a una vista diciendo que el correo ya esta registrado
		}
		
		Trabajador trabajador = new Trabajador(nombre, contrasena, documento, correo);
		trabajadorRepository.save(trabajador);
		return ""; // retorna a la vista después de guardar el trabajor.
	}
	
	
	@GetMapping("/prueba")
	public void probando() {
		System.out.println("entro en prueba");
		if(trabajadorRepository.findByCorreo("kevin@gmail.com") != null) {
			System.out.println("el correo existe");
		}
		System.out.println("guardo el trabajador");
		
	}

}
