package com.teste;

import org.junit.jupiter.api.Test;
import com.example.Livraria.services.LivroService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;


@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration
class TesteLivro {
	
	@Autowired
	LivroService livroService;
	
	
	@Test
	public void TesteCadastro() {
		
		livroService.cadastrarLivro(null, null, null, null, null, null, null, null, null, null, null);
		
	}
	

}
