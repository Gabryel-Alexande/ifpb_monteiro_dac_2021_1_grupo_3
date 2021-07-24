package com.teste;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.*;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.internal.handler.MockHandlerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.example.Livraria.model.Categoria;
import com.example.Livraria.services.CategoriaService;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration

class TesteCategoria {
	
	@Spy
	static Categoria categoria;
	@Spy
	static CategoriaService categoriaService;
	@BeforeAll
	public static void setUp() {
		categoria = new Categoria("literatura");
		categoriaService = new CategoriaService();
		
	}
	@Test(expected = IllegalArgumentException.class)
	public void testeCadastroCategoria() {
		//assertThrows(categoriaService.criarCategoria(""),()->IllegalArgumentException.class);
		
		
	}
	@AfterAll
	public static void setDown(){
		
	}
	

}
