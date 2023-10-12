package com.ufps.webdificar.proyecto.auth.handler;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

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
import jakarta.servlet.http.HttpSession;

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
		
		
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		boolean rolUser = authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()).contains("ROLE_USER") && authorities.size() == 1;
		boolean rolUserST = authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()).contains("ROLE_USERST") && authorities.size() == 1;
		boolean rolUserSCA = authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()).contains("ROLE_USERSCA") && authorities.size() == 1;		
		boolean rolAdmin = authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()).contains("ROLE_ADMIN");
		
		// Se agrega el modelo de trabajador al sesion para que en el controlador de listar proyecto recuperarlo.
		HttpSession session = request.getSession();
		session.setAttribute("trabajador", trabajador);

		response.sendRedirect("/proyecto/listar");
		super.onAuthenticationSuccess(request, response, authentication);
	}

	
	
	
}
