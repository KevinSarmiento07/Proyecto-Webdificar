package com.ufps.webdificar.proyecto.auth.handler;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.support.SessionFlashMapManager;

import com.ufps.webdificar.proyecto.entities.Trabajador;
import com.ufps.webdificar.proyecto.service.implementations.JpaUserDetailsService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{
	
	
	@Autowired 
	private JpaUserDetailsService userDetailsService;
	

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		System.out.println("ENTRO EN onAUTH");
		Trabajador trabajador = userDetailsService.findByUsername(authentication.getName());
		
		System.out.println(trabajador);
		SessionFlashMapManager flashMapManager = new SessionFlashMapManager();
		
		FlashMap flashMap = new FlashMap();
		
		flashMap.put("success", "Bienvenido " + trabajador.getNombre() + " haz, iniciado sesión con éxito");
		
		flashMapManager.saveOutputFlashMap(flashMap, request, response);
		
		if(authentication != null) {
			logger.info("El trabajador " + trabajador.getNombre() + " ha iniciado sesión");
		}
		
		//Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		
		
		
		super.onAuthenticationSuccess(request, response, authentication);
	}

	
	
	
}
