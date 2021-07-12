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
public class SecurityConfig extends WebSecurityConfigurerAdapter{

@Autowired	
private  AutenticacaoService autenticacaoService;
	
	
@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());
	}

@Override
protected void configure(HttpSecurity http) throws Exception {
	// TODO Auto-generated method stub
	http.authorizeRequests()
	.antMatchers(HttpMethod.GET,"/livraria/cadastro").permitAll()
	.antMatchers(HttpMethod.GET,"/livraria/login").permitAll()
	.antMatchers(HttpMethod.GET,"/livraria/home").permitAll()
	//.antMatchers(HttpMethod.GET,"/livraria/home/carrinho").hasAuthority("USUARIO LOGADO")
	.anyRequest()
	.authenticated()
	.and()
	.formLogin().defaultSuccessUrl("/livraria/home");
	
	
	
	//hasAuthority tá dando erro 
}


//public static void main(String[] args) {
//	System.out.println(new BCryptPasswordEncoder().encode("GGGaaa!!8888822"));
//}

}
