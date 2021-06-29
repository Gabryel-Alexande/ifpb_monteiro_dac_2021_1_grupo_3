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

	public void run(String... args) {
		boolean con = true;

		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
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
		while (con) {

			System.out.println("0 - Finalizar" + "\n1 - Registrar Novo Usuário V"
					+ "\n2 - Consultar Usuário pelo E-Mail V" + "\n3 - Cadastrar Autor V" + "\n4 - Alterar Autor V"
					+ "\n5 - Cadastrar Livro V" + "\n6 - Alterar Livro V" + "\n7 - Excluir Livro V"
					+ "\n8 - Consultar os 5 mais baratos V" + "\n9 - Consultar todos os Livros V"
					+ "\n10 - Criar categoria V" + "\n11 - Adicionar o Livro ao Carrinho V"
					+ "\n12 - Remover livro do carrinho V" + "\n13 - Fazer Pedido V" + "\n14 - Cancelar pedido V"
					+ "\n15 - Listar Pedidos V" + "\n16 - Criar Endereco V" + "\n17 - Adcionar Endereco V"
					+ "\n18 - Remover Endereco V" + "\n19 - Listar Endereco V" + "\n20 - Editar categoria V"
					+ "\n21 - Ecluir categoria V" + "\n22 - Listar categoria V" + "\n23 - Criar editora V"
					+ "\n24 - Editar editora V" + "\n25 - Adcionar endereço a editora V" + "\n26 - Listar editora V"
					+ "\n27 - Ver carrinho V" + "\n28 - Remover endereço de editora V" + "\n29 - Excluir editora V" 
					+ "\n30 - Editar usuario V" + "\n31 - Buscar Livro pelo nome V" + "\n32 - Buscar Livro pela categoria V"
					+ "\n33 - Buscar Livro pelo isbn V");

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
					System.out.println("Ano de nascimento");
					anoLancamento = Integer.parseInt(input.nextLine());
					System.out.println("Você é Administrador ? (1 = Sim: 2= Não");
					int opadm = Integer.parseInt(input.nextLine());

					boolean adm = true;
					if (opadm == 2) {
						adm = false;
					}
					//controllerUsuario.cadatrarUsusario(cpf, nome, email, senha, adm, anoLancamento);
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
					} catch (NoSuchElementException e) {
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
					BufferedImage imagem = ImageIO.read(new File("imagem.jpg"));
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
					preco = new BigDecimal(Integer.parseInt(input.nextLine()));
					int sair = 0;
					List<Long> idsCategorias = new ArrayList<Long>();
					while (sair != 2) {
						System.out.println("Digite um ID de categoria: ");
						idsCategorias.add(Long.parseLong(input.nextLine()));
						System.out.println("Deseja colocar mais alguma categoria ? 1 para sim e 2 para não: ");
						sair = Integer.parseInt(input.nextLine());
					}
					controllerLivro.cadastrarLivro(isbn, tituloLivro, idsCategorias, descricao, preco, edicao,
							anoLancamento, idEditora, fotosLivro, autores, quantidade);
					System.out.println("Livro criado!");

					break;
				case 6:
					System.out.println("Id da Livro: ");
					Long idLivro = Long.parseLong(input.nextLine());
					System.out.println("Digite o ISBN: ");
					isbn = input.nextLine();
					System.out.println("Titulo do livro: ");
					tituloLivro = input.nextLine();
					System.out.println("Descrição do livro: ");
					descricao = input.nextLine();
					System.out.println("preço do livro: ");
					preco = new BigDecimal(Integer.parseInt(input.nextLine()));

					System.out.println("Ano de lançamento do livro: ");
					anoLancamento = Integer.parseInt(input.nextLine());

					System.out.println("Edições do livro: ");
					edicao = input.nextLine();

					System.out.println("Id da Editora: ");
					idEditora = Long.parseLong(input.nextLine());

					System.out.println("Digite a quantitade de livros em estoque: ");
					quantidade = Integer.parseInt(input.nextLine());

					controllerLivro.alterarLivro(idLivro, isbn, tituloLivro, descricao, preco, edicao, anoLancamento,
							idEditora, quantidade);
					System.out.println("Livro alterado!");
					break;

				case 7:
					System.out.println("ISBN do livro para remove-lo: ");
					isbn = input.nextLine();
					controllerLivro.removerLivro(isbn);
					System.out.println("Livro removido!");
					break;

				case 8:
					for (Object livro : controllerLivro.listarCincoLivrosComMenorPreco()) {
						System.out.println(livro.toString() + "\n-------------------------------");
					}
					break;
				case 9:
					System.out.println("Digite qual página deseja acessar");
					quantidade = Integer.parseInt(input.nextLine());
					for (Object livro : controllerLivro.listarLivros("tituloLivro", 2, quantidade)) {
						System.out.println(livro.toString() + "\n-------------------------------");
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
					System.out.println("Livro adcionado ao carrinho!");
					break;
				case 12:
					System.out.println("Email do cliente: ");
					email = input.nextLine();
					System.out.println("Digite o id do livro no carrinho: ");
					id = Long.parseLong(input.nextLine());
					controllerUsuario.removerDoCarinho(id, email);
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
					id = Long.parseLong(input.nextLine());
					controllerUsuario.cancelarPedido(id, email);
					System.out.println("Pedido cancelado!");
					break;
				case 15:
					System.out.println("Email do cliente: ");
					email = input.nextLine();
					for (Object pedido : controllerUsuario.listarPedidos(email)) {
						System.out.println(pedido.toString() + "\n-------------------------------");
					}
					break;
				case 16:

//					System.out.println("Digite o CEP: ");
//					String cep = input.nextLine();
//					System.out.println("Digite a rua: ");
//					String rua = input.nextLine();
//					System.out.println("Digite o estado: ");
//					String estado = input.nextLine();
//					System.out.println("Digite a cidade: ");
//					String cidade = input.nextLine();
//					System.out.println("Digite o pais: ");
//					String pais = input.nextLine();
//					System.out.println("Digite o bairro: ");
//					String bairro = input.nextLine();
//					System.out.println("Digite o numero da casa: ");
//					String numeroCasa = input.nextLine();
//					System.out.println("Digite o complemento");
//					String complemento = input.nextLine();
//					  try{
//					  
//					  controllerEndereco.adcionarEndereco(cep, rua, estado, cidade,complemento,
//					  pais, bairro, numeroCasa); }catch(Exception e ) {
//					  System.out.println(e.getMessage()); } System.out.println("Endereco criado!");
					break;

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
					System.out.println("Digite o complemento: ");
					String complemento = input.nextLine();
					controllerUsuario.adcionarEndereco(email, cep, rua, estado, cidade, complemento, pais, bairro,
							numeroCasa);
					System.out.println("Endereco adcionado!");
					break;
				case 18:
					System.out.println("Email do cliente: ");
					email = input.nextLine();
					System.out.println("Digite o id do endreco:");
					id = Long.parseLong(input.nextLine());
					controllerUsuario.removerEndereco(id, email);
					System.out.println("Endereco removido!");
					break;
				case 19:
					System.out.println("Email do cliente: ");
					email = input.nextLine();
					for (Object endereco : controllerEndereco.listarEnderecos(email)) {
						System.out.println(endereco.toString() + "\n-------------------------------");
					}
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
//					System.out.println("Digite o id da categoria:");
//					id = Long.parseLong(input.nextLine());
//					controllerCategoria.excluirCategoria(id);
//					System.out.println("Categoria excluida!");
					break;
				case 22:
					for (Object categoria : controllerCategoria.listarCategoria()) {
						System.out.println(categoria.toString() + "\n-------------------------------");
					}
					break;
				case 23:
					System.out.println("Digite o nome da editora: ");
					nome = input.nextLine();
					controllerEditora.cadastrarEditora(nome);
					System.out.println("Editora criada");
					break;
				case 25:
//					System.out.println("Digite o id da editora:");
//					id = Long.parseLong(input.nextLine());
//					System.out.println("Digite o CEP: ");
//					cep = input.nextLine();
//					System.out.println("Digite a rua: ");
//					rua = input.nextLine();
//					System.out.println("Digite o estado: ");
//					estado = input.nextLine();
//					System.out.println("Digite a cidade: ");
//					cidade = input.nextLine();
//					System.out.println("Digite o pais: ");
//					pais = input.nextLine();
//					System.out.println("Digite o bairro: ");
//					bairro = input.nextLine();
//					System.out.println("Digite o numero da casa: ");
//					numeroCasa = input.nextLine();
//					System.out.println("Digite o complemento: ");
//					complemento = input.nextLine();
//					controllerEditora.adcionarEndereco(cep, rua, estado, cidade, complemento, pais, bairro, numeroCasa,
//							id);
//					System.out.println("Endereco criado!");
					break;
				case 24:
					System.out.println("Digite o id da editora:");
					id = Long.parseLong(input.nextLine());
					System.out.println("Digite o novo nome");
					nome = input.nextLine();
					controllerEditora.editarEndereco(nome, id);
					System.out.println("Editora atualizada!");
					break;
				case 26:

					for (Object editora : controllerEditora.listarEditoras()) {
						System.out.println(editora.toString() + "\n-------------------------------");
					}
					break;
				case 27:
					System.out.println("Email do cliente: ");
					email = input.nextLine();
					for (Object item : controllerUsuario.verCarrinho(email)) {
						System.out.println(item.toString() + "\n-------------------------------");
					}

					break;
				case 28:
//					System.out.println("Digite o id da editora:");
//					id = Long.parseLong(input.nextLine());
//					controllerEditora.removerEndereco(id);
					break;
				case 29:
					System.out.println("Digite o id da editora:");
					id = Long.parseLong(input.nextLine());
					controllerEditora.excluirEditora(id);
					System.out.println("Editora Excluida!");
					break;
				case 30:
					System.out.println("Nome:");
					nome = input.nextLine();
					System.out.println("Email:");
					email = input.nextLine();
					System.out.println("Senha:");
					senha = input.nextLine();
					System.out.println("CPF:");
					cpf = input.nextLine();
					System.out.println("Ano de nascimento");
					anoLancamento = Integer.parseInt(input.nextLine());
					System.out.println("Você é Administrador ? (1 = Sim: 2= Não");
					opadm = Integer.parseInt(input.nextLine());

					adm = true;
					if (opadm == 2) {
						adm = false;
					}
					//controllerUsuario.alteraUsuario(email, cpf, nome, senha, adm, anoLancamento);
					System.out.println("Usuario atualizado!");
					break;
				case 31:
					System.out.println("Nome:");
					nome = input.nextLine();
					for (Object livro : controllerLivro.bucarLivroPorNome(nome)) {
						System.out.println(livro.toString());
					}
					break;
				case 32:
					System.out.println("Digite o id da categoria:");
					id = Long.parseLong(input.nextLine());
					for (Object livro : controllerLivro.bucarLivrosPorCategoria(id)) {
						System.out.println(livro.toString());
					}
					break;
				case 33:
					System.out.println("ISBN do livro: ");
					isbn = input.nextLine();
					System.out.println(controllerLivro.bucarLivrosPorId(isbn));
					break;
				
				}
			} catch (NotFoundException | LoginException | IOException e) {
				System.out.println(e.getMessage());
			}
			System.out.println("-------------------------------");
		}
	}
}
