package com.ufps.webdificar.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ufps.webdificar.proyecto.entities.Trabajador;
import com.ufps.webdificar.proyecto.repositories.TrabajadorRepository;
import com.ufps.webdificar.proyecto.service.implementations.JpaUserDetailsService;

@org.springframework.stereotype.Controller
@RequestMapping("/register")
public class RegisterController {
	
	@Autowired
	private TrabajadorRepository trabajadorRepository;
	
	@Autowired
	private JpaUserDetailsService jpaUserDetailsService; 
	
	@GetMapping("/login")
	public String iniciarSesion() {
		return "views/login";
	}
	
	
//	@PostMapping
//	public String procesarIniciarSesion(@RequestParam("username") String username, @RequestParam("contrasena") String contrasena, Model model) {
//		Trabajador trabajador = trabajadorRepository.findByUsername(username);
//		if( (trabajador== null || !trabajador.getPassword().equals(contrasena))) {
//			System.out.println("entro al if");
//			return "views/login"; // aqui debe redireccionar a una pagina donde diga que el usuario o contraseña son incorrectos 
//		}
//		
//		model.addAttribute("trabajador", trabajador);
//		return "/proyecto/listar"; //redireccionar a la vista principal
//	}
	
	@GetMapping("/registro")
	public String registro(Model model) {
		
		Trabajador trabajador = new Trabajador();
		model.addAttribute("trabajador", trabajador);
		
		return "views/register";
	}
	
	@PostMapping("/registro")
	public String procesarRegistro(@RequestParam("nombre") 
	String nombre, @RequestParam("password") 
	String contrasena,@RequestParam("documento") 
	String documento,  @RequestParam("username") 
	String username) {
		if(trabajadorRepository.findByUsername(username) != null) {
			return "/login/registro"; // aqui debe reddireccionar a una vista diciendo que el correo ya esta registrado
		}
		
		Trabajador trabajador = new Trabajador(nombre, contrasena, documento, username);
		jpaUserDetailsService.save(trabajador);
		return "views/login"; // retorna a la vista después de guardar el trabajor.
	}

	
	

}
