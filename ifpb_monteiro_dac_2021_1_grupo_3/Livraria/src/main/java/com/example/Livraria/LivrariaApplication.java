package com.example.Livraria;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.Livraria.controllers.ControllerAutor;
import com.example.Livraria.controllers.ControllerCategoria;
import com.example.Livraria.controllers.ControllerEditora;
import com.example.Livraria.controllers.ControllerEndereco;
import com.example.Livraria.controllers.ControllerLivro;
import com.example.Livraria.controllers.ControllerUsuario;
import com.example.Livraria.exeception.CPFException;
import com.example.Livraria.exeception.LoginException;

import javassist.NotFoundException;

@SpringBootApplication
public class LivrariaApplication implements CommandLineRunner {
	@Autowired
	ControllerUsuario controllerUsuario;
	@Autowired
	ControllerAutor controllerAutor;
	@Autowired
	ControllerCategoria controllerCategoria;
	@Autowired
	ControllerEditora controllerEditora;
	@Autowired
	ControllerLivro controllerLivro;
	@Autowired
	ControllerEndereco controllerEndereco;

	public static void main(String[] args) {
		SpringApplication.run(LivrariaApplication.class, args);
	}

	public void run(String... args){
		boolean con = true;

		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		Scanner input2 = new Scanner(System.in);
		String email;
		String nome;
		String senha;
		String isbn;
		String tituloLivro;
		String descricao;
		BigDecimal preco;
		String edicao;
		Integer anoLancamento;
		Long idEditora;
		List<Long> autores;
		Long idDoAutor;
		Integer quantidade;
		Long idCategoria;
		while (con) {

			System.out.println("0- Finalizar" + "\n1 -Registrar Novo Usuário V" + "\n2 -Consultar Usuário pelo E-Mail V"
					+ "\n3- Cadastrar Autor V" + "\n4 -Alterar Autor V" + "\n5 -Cadastrar Livro" + "\n6 -Alterar Livro"
					+ "\n7 -Excluir Livro" + "\n8 -Consultar os 5 mais baratos" + "\n9 -Consultar todos os Livros"
					+ "\n10 -Criar categoria V" + "\n11 - Adicionar o Livro ao Carrinho(pelo id)"
					+ "\n12 - Remover livro do carrinho" + "\n13 -Fazer Pedido" + "\n14 - Cancelar pedido"
					+ "\n15 - Listar Pedidos" + "\n16 - Criar Endereco" + "\n17 - Adcionar Endereco"
					+ "\n18 - Remover Endereco" + "\n19 - Listar Endereco" + "\n20 - Editar categoria V"
					+ "\n21 - Ecluir categoria" + "\n22 - Listar categoria V" + "\n23 - Criar editora V"
					+ "\n24 - Editar editora" + "\n25 - Adcionar endereço a editora" + "\n26 - Listar editora V(falta o endereço)"
					+ "\n27 - Ver carrinho");

			System.out.println("-------------------------------");
			int opcao = Integer.parseInt(input.nextLine());
			System.out.println("-------------------------------");
			try {
				switch (opcao) {
				case 0:
					con = false;
					System.out.println("Finalizando programa!");
					break;
				case 1:
					
					

					System.out.println("Nome:");
					nome = input.nextLine();
					System.out.println("Email:");
					email = input.nextLine();
					System.out.println("Senha:");
					senha = input.nextLine();
					System.out.println("CPF:");
					String cpf = input.nextLine();
					System.out.println("Você é Administrador ? (1 = Sim: 2= Não");
					int opadm = Integer.parseInt(input.nextLine());

					boolean adm = true;
					if (opadm == 2) {
						adm = false;
					}
					controllerUsuario.cadatrarUsusario(cpf, nome, email, senha, adm);
					System.out.println("Cadastrado com Sucesso !!");
					break;
				case 2:
					
					System.out.println("Digite o Email:");
					email = input.nextLine();
					Object usuario = controllerUsuario.consultarPorEmail(email);
					System.out.println(usuario.toString());
					
					break;

				case 3:
					System.out.println("Digite o Nome do Autor: ");
					nome = input.nextLine();
					System.out.println("Digite o Email: ");
					email = input.nextLine();
					System.out.println("Digite uma senha: ");
					senha = input.nextLine();
					controllerAutor.cadastrarAutor(nome, email, senha);
					System.out.println("Autor criado!");
					break;

				case 4:
					System.out.println("Digite o ID: ");
					Long id = Long.parseLong(input.nextLine());
					System.out.println("Digite o novo nome: ");
					email = input.nextLine();
					System.out.println("Digite uma senha: ");
					senha = input.nextLine();
					try {
					controllerAutor.alterarAutor(id, email, senha);
					System.out.println("Autor alterado!");
					}catch (NoSuchElementException e) {
						System.out.println(e.getMessage());
					}
					break;

				case 5:
					System.out.println("Digite o ISBN: ");
					isbn = input.nextLine();
					System.out.println("Titulo do livro: ");
					tituloLivro = input.nextLine();
				
				
					System.out.println("Descrição do livro: ");
					descricao = input.nextLine();
					
					System.out.println("Edições do livro: ");
					edicao = input.nextLine();
				

					List<Image> fotosLivro = new ArrayList<Image>();
//					int sairImagem = 0;
//					while (sairImagem != 2) {
//						System.out.println("Digite o endereço da imagem: ");
//						String edereçoDaImagem = input.nextLine();
//						BufferedImage imagem = ImageIO.read(new File(edereçoDaImagem));
//						fotosLivro.add(imagem);
//						System.out.println("Deseja colocar mais alguma Imagem ? 1 para sim e 2 para não: ");
//						sairImagem = Integer.parseInt(input.nextLine());
//					}
					BufferedImage imagem = ImageIO.read(new File("\\Users\\ytall\\Downloads\\ISAC\\fl studio\\imagensParaDac\\download.jpg"));
					fotosLivro.add(imagem);
					int sairAutor = 0;
					autores = new ArrayList<Long>();
					
					while (sairAutor != 2) {
						System.out.println("Digite o ID do Autor: ");
						idDoAutor = Long.parseLong(input.nextLine());
						autores.add(idDoAutor);
						System.out.println("Deseja colocar mais algum Autor ? 1 para sim e 2 para não: ");
						sairAutor = Integer.parseInt(input.nextLine());
					}
					System.out.println("Digite a quantitade de livros em estoque: ");
					quantidade = Integer.parseInt(input.nextLine());
					System.out.println("Ano de lançamento do livro: ");
					anoLancamento = Integer.parseInt(input.nextLine());
					System.out.println("Id da Editora: ");
					idEditora = Long.parseLong(input.nextLine());
					System.out.println("preço do livro: ");
					preco = input.nextBigDecimal();
					int sair = 0;
					List<Long> idsCategorias = new ArrayList<Long>();
//					while (sair != 2) {
//						System.out.println("Digite um ID de categoria: ");
//						System.out.println(input.nextLine());
//						idCategoria = input.nextLong();
//						idsCategorias.add(idCategoria);
//						System.out.println("Deseja colocar mais alguma categoria ? 1 para sim e 2 para não: ");
//						sair = Integer.parseInt(input2.nextLine());
//					}
					idsCategorias.add((long) 3);
					controllerLivro.cadastrarLivro(isbn, tituloLivro, idsCategorias, descricao, preco, edicao,
							anoLancamento, idEditora, fotosLivro, autores, quantidade);
					System.out.println("Livro criado!");
					
					break;
				case 6:
					System.out.println("Digite o ISBN: ");
					isbn = input.nextLine();
					System.out.println("Titulo do livro: ");
					tituloLivro = input.nextLine();
					System.out.println("Descrição do livro: ");
					descricao = input.nextLine();
					System.out.println("preço do livro: ");
					preco = input.nextBigDecimal();
				
					System.out.println("Ano de lançamento do livro: ");
					anoLancamento = Integer.parseInt(input.nextLine());
					
					System.out.println("Edições do livro: ");
					edicao = input.nextLine();
					System.out.println("Id da Editora: ");
					idEditora = Long.parseLong(input.nextLine());
					System.out.println("Digite a quantitade de livros em estoque: ");
					quantidade = Integer.parseInt(input.nextLine());

					controllerLivro.alterarLivro(isbn, tituloLivro, descricao, preco, edicao, anoLancamento, idEditora,
							quantidade);
					System.out.println("Livro alterado!");
					break;

				case 7:
					System.out.println("ISBN do livro para remove-lo: ");
					isbn = input.nextLine();
					controllerLivro.removerLivro(isbn);
					System.out.println("Livro removido!");
					break;

				case 8:
					System.out.println(controllerLivro.listarCincoLivrosComMenorPreco().toString());
					break;
				case 9:
					for (Object livro : controllerLivro.listarLivros()) {
						
						System.out.println(livro.toString());
					}
					break;
				case 10:
					System.out.println("Digite o Nome da categoria: ");
					nome = input.nextLine();
					controllerCategoria.criarCategoria(nome);
					System.out.println("Categoria criada!");
					break;

				case 11:
					System.out.println("ISBN do livro: ");
					isbn = input.nextLine();
					System.out.println("Email do cliente: ");
					email = input.nextLine();
					System.out.println("Quantidade de livro: ");
					Integer quantidadeLivro = Integer.parseInt(input.nextLine());
					controllerUsuario.adcionarAoCarinho(isbn, quantidadeLivro, email);
					System.out.println("Livroadcionado ao carrinho!");
					break;
				case 12:
					System.out.println("indice do livro no carrinho: ");
					int indice = Integer.parseInt(input.nextLine());
					System.out.println("Email do cliente: ");
					email = input.nextLine();
					controllerUsuario.removerDoCarinho(indice, email);
					System.out.println("Livro removido do carrinho!");
					break;
				case 13:

					System.out.println("Email do cliente: ");
					email = input.nextLine();
					controllerUsuario.comprarLivro(email);
					System.out.println("Pedido realizado com sucesso!");
					break;
				case 14:
					System.out.println("Email do cliente: ");
					email = input.nextLine();
					System.out.println("Digite o id do pedido:");
					id = input.nextLong();
					controllerUsuario.cancelarPedido(id, email);
					System.out.println("Pedido cancelado!");
					break;
				case 15:
					System.out.println("Email do cliente: ");
					email = input.nextLine();
					System.out.println(controllerUsuario.listarPedidos(email).toString());
				case 16:
					/*
					 * System.out.println("Digite o CEP: "); String cep = input.nextLine();
					 * System.out.println("Digite a rua: "); String rua = input.nextLine();
					 * System.out.println("Digite o estado: "); String estado = input.nextLine();
					 * System.out.println("Digite a cidade: "); String cidade = input.nextLine();
					 * System.out.println("Digite o pais: "); String pais = input.nextLine();
					 * System.out.println("Digite o bairro: "); String bairro = input.nextLine();
					 * System.out.println("Digite o numero da casa: "); String numeroCasa =
					 * input.nextLine(); System.out.println("Digite o complemento"); String
					 * complemento = input.nextLine();
					 */
					
					/*try{
						
						controllerEndereco.adcionarEndereco(cep, rua, estado, cidade,complemento, pais, bairro, numeroCasa);
					}catch(Exception e ) {
						System.out.println(e.getMessage());
					}
					System.out.println("Endereco criado!");
					break;*/
				case 17:
					
					System.out.println("Email do cliente: ");
					email = input.nextLine();
					System.out.println("Digite o CEP: ");
					String cep = input.nextLine();
					System.out.println("Digite a rua: ");
					String rua = input.nextLine();
					System.out.println("Digite o estado: ");
					String estado = input.nextLine();
					System.out.println("Digite a cidade: ");
					String cidade = input.nextLine();
					System.out.println("Digite o pais: ");
					String pais = input.nextLine();
					System.out.println("Digite o bairro: ");
					String bairro = input.nextLine();
					System.out.println("Digite o numero da casa: ");
					String numeroCasa = input.nextLine();
					System.out.println("Digite o complemento");
					String complemento  = input.nextLine();
					controllerUsuario.adcionarEndereco(email, cep, rua, estado, cidade, complemento, pais, bairro, numeroCasa);
					System.out.println("Endereco adcionado!");
					break;
				case 18:
					System.out.println("Email do cliente: ");
					email = input.nextLine();
					System.out.println("Digite o id do endreco:");
					id = input.nextLong();
					controllerUsuario.removerEndereco(id, email);
					System.out.println("Endereco removido!");
					break;
				case 19:
					System.out.println(controllerEndereco.listarEnderecos());
					break;
				case 20:
					System.out.println("Digite o id da categoria:");
					id = Long.parseLong(input.nextLine());
					System.out.println("Digite o novo nome da categoria: ");
					nome = input.nextLine();
					controllerCategoria.editarCategoria(id, nome);
					System.out.println("Categoria editada!");
					break;
				case 21:
					System.out.println("Digite o id da categoria:");
					id = input.nextLong();
					controllerCategoria.excluirCategoria(id);
					System.out.println("Categoria excluida!");
					break;
				case 22:
					for (Object categoria : controllerCategoria.listarCategoria()) {
						System.out.println(categoria.toString());
					}
					break;
				case 23:
					System.out.println("Digite o nome da editora: ");
					nome = input.nextLine();
					controllerEditora.cadastrarEditora(nome);
					System.out.println("Editora criada");
					break;
				case 25:
					System.out.println("Digite o id da editora:");
					id = Long.parseLong(input.nextLine());
					controllerEditora.removerEndereco(id);
					System.out.println("Editora excluida!");
					break;
				case 24:
					System.out.println("Digite o id da editora:");
					id = Long.parseLong(input.nextLine());
					System.out.println("Digite o id do endereco");
					Long idEndereco = input.nextLong();
					controllerEditora.adcionarEndereco(idEndereco, id);
					System.out.println("Endereço adcionado!");
					break;
				case 26:
					
					for (Object editora : controllerEditora.listarEditoras()) {
						System.out.println(editora.toString());
					}
					break;
				case 27:
					System.out.println("Email do cliente: ");
					email = input.nextLine();
					System.out.println(controllerUsuario.verCarrinho(email).toString());
					break;
				}
			} catch (NotFoundException | LoginException | IOException | CPFException e) {
				System.out.println(e.getMessage());
			}
			System.out.println("-------------------------------");
		}
	}
}
