package com.example.Livraria.facade;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.Livraria.model.Livro;
import com.example.Livraria.services.LivroService;

@Service
public class LivroFacade {
	
	@Autowired
	private LivroService livroService;
	
	public Page<Livro> paginarLivros (Integer pagina, List<Long> categorias, String	stringDeBusca){
		
		if(!stringDeBusca.equals("")) {
			return livroService.bucarLivroPorNome(stringDeBusca.toUpperCase(),pagina);
		}
		else if( categorias !=null && !categorias.contains(-1l)) {
			return livroService.listarLivrosCategoria(categorias,pagina);
		}
		
		return livroService.listarTodosOsLivros(pagina);
		
	}
	
	
	public List<Integer> criarBotoes(Integer quantidade, Integer pagina){
		List<Integer>paginas =new ArrayList<>();
		if (pagina >1) {
			paginas.add(pagina -1);
		}
		for (int i =pagina;i<=quantidade;i++) {
			paginas.add(i);
			if(paginas.size()==5) {
				break;
			}
		}
		
		return paginas;
	}
	
	


}
