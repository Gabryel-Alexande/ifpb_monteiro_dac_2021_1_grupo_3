package com.example.Livraria.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.Livraria.model.Usuario;
import com.example.Livraria.repositorio.UsuarioRepositorio;

@Service
public class AutenticacaoService  implements UserDetailsService{

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		Usuario usuarioRecuperado = usuarioRepositorio.findByEmail(username);
		
		if(usuarioRecuperado != null)
			return usuarioRecuperado;
		
		throw new UsernameNotFoundException("USUARIO NÃO ENCONTRADO"); 
		
		
		
		
	
	}

}
