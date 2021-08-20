package com.example.Livraria.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.Livraria.services.AutenticacaoService;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private AutenticacaoService autenticacaoService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/livraria/publico/**").permitAll()
		.antMatchers("/cadastro").permitAll()
		.antMatchers("/login").permitAll()
		.antMatchers("/logout").permitAll()
		.antMatchers("/livaria/protegido/**").hasAuthority("CLIENTE")
		.antMatchers("/livraria/adm/**").permitAll()
		.antMatchers("/pagamentos/**").permitAll()
		//.hasAuthority("ADMINISTRADOR")
		.antMatchers("/css/**","/js/**").permitAll()
		
		.anyRequest().authenticated()
		.and()
		.formLogin().loginPage("/login")
		.defaultSuccessUrl("/livraria/publico/home",true)
		.and()
		.csrf().disable()
		.logout().logoutUrl("/logout").logoutSuccessUrl("/livraria/publico/home");
		;
	}

}
