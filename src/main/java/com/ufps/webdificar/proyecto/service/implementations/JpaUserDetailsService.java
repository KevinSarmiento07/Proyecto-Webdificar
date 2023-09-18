package com.ufps.webdificar.proyecto.service.implementations;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.ufps.webdificar.proyecto.entities.Role;
import com.ufps.webdificar.proyecto.entities.Trabajador;
import com.ufps.webdificar.proyecto.repositories.TrabajadorRepository;
import com.ufps.webdificar.proyecto.service.interfaces.TrabajadorInterface;

import jakarta.transaction.Transactional;

public class JpaUserDetailsService implements UserDetailsService, TrabajadorInterface{
	
	protected final Log logger = LogFactory.getLog(this.getClass());
	
	
	@Autowired
	private TrabajadorRepository trabajadorDao;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	
	@org.springframework.transaction.annotation.Transactional(readOnly = true)
	public Trabajador findByUsername(String username) {
		return trabajadorDao.findByUsername(username);
	}
	
	
	

	@Override
	public List<Trabajador> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Trabajador getById(Trabajador trabajador) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Trabajador create(Trabajador trabajador) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Trabajador update(Trabajador trabajador) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Trabajador getByCorreo(String correo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Trabajador trabajador = trabajadorDao.findByUsername(username);
		
		if(trabajador == null) {
			 throw new UsernameNotFoundException("El usuario no existe") ;
		}
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		for(Role role : trabajador.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
		}
		
		if(authorities.isEmpty()) {
			throw new UsernameNotFoundException("Error en el login, el usuario: " + username + "no tiene roles asignados");
		}
		
		return new User(username, trabajador.getPassword(), trabajador.isEnabled(), true, true, true, authorities);
	}

}
