package com.ufps.webdificar.proyecto.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ufps.webdificar.proyecto.service.implementations.JpaUserDetailsService;

@Controller
public class LoginController {

	
	@Autowired
	private JpaUserDetailsService userDetailsService;
	
	@GetMapping("/login")
	public String login(@RequestParam( value = "error", required = false)String error , @RequestParam(value = "logout" , required = false)String logout , Model model, Principal principal, RedirectAttributes flash) {
		
		if(principal != null) {
			flash.addFlashAttribute("info", "Ya has iniciado sesion anteriormente");
			return "redirect:/";
		}
		
		if(logout != null) {
			model.addAttribute("success", "Has cerrado sesión con exito");
		}
		
		if(error != null) {
			System.out.println("entró en el if de ERROR");
			model.addAttribute("errorLogin", "Error al iniciar sesión, usuario o contraseña incorrecta");
			
		}
		
		model.addAttribute("titulo","BoxerCrossGym");
		
		return "views/login";
	}
}
