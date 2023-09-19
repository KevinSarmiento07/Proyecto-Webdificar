package com.ufps.webdificar.proyecto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.ufps.webdificar.proyecto.auth.handler.LoginSuccessHandler;
import com.ufps.webdificar.proyecto.service.implementations.JpaUserDetailsService;

@Configuration
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig {
	
	@Autowired
	private LoginSuccessHandler successHandler;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private JpaUserDetailsService userDetailsService;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http)throws Exception {
		System.out.println("Entró en filter chain");
		http.authorizeHttpRequests((authz) -> {
			try {
				authz.requestMatchers("/css/**", "/js/**", "/images/**", "/adminlte/**", "/fontawesome/**", "/").permitAll()
				.requestMatchers("/**").hasAnyRole("USER", "ADMIN")
				.anyRequest().authenticated();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}).formLogin(login -> login.loginPage("/login").successHandler(successHandler).permitAll());
		
		return http.build();
	}
	
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder build) throws Exception {
 
        build.userDetailsService(userDetailsService)
        .passwordEncoder(passwordEncoder);
    }

}
