package com.example.Livraria;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.imageio.ImageIO;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.Livraria.controllers.ControllerAutor;
import com.example.Livraria.controllers.ControllerCategoria;
import com.example.Livraria.controllers.ControllerEditora;
import com.example.Livraria.controllers.ControllerLivro;
import com.example.Livraria.controllers.ControllerUsuario;
import com.example.Livraria.model.Editora;

@SpringBootApplication
public class LivrariaApplication {

	public static void main(String[] args) {
		SpringApplication.run(LivrariaApplication.class, args);
	}

	public void run(String... args) throws Exception {
		boolean con = true;
		ControllerUsuario controllerUsuario = new ControllerUsuario();
		ControllerAutor controllerAutor = new ControllerAutor();
		ControllerCategoria controllerCategotia = new ControllerCategoria();
		ControllerEditora controllerEditora = new ControllerEditora();
		ControllerLivro controllerLivro = new ControllerLivro();
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

			System.out.println("0- Finalizar" + "\n1 -Registrar Novo Usuário" + "\n2 -Consultar Usuário pelo E-Mail"
					+ "\n3- Cadastrar Autor " + "\n4 -Alterar Autor" + "\n5 -Cadastrar Livro" + "\n6 -Alterar Livro"
					+ "\n7 -Excluir Livro" + "\n8 -Consultar os 5 mais baratos" + "\n9 -Consultar todos os Livros"
					+ "\n10 -Criar categoria" + "\n11 - Adicionar o Livro ao Carrinho(pelo id)" + "\n12 -Fazer Pedido");

			int opcao = Integer.parseInt(input.nextLine());
			switch (opcao) {
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
				if (opadm == 2)
					adm = false;

				try {
					controllerUsuario.cadatrarUsusario(cpf, nome, email, senha, adm);
					System.out.println("Cadastrado com Sucesso !!");
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
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
				break;

			case 4:
				System.out.println("Digite o ID: ");
				Long id = Long.parseLong(input.nextLine());
				System.out.println("Digite o Email: ");
				email = input.nextLine();
				System.out.println("Digite uma senha: ");
				senha = input.nextLine();
				controllerAutor.alterarAutor(id, email, senha);
				break;

			case 5:
				System.out.println("Digite o ISBN: ");
				isbn = input.nextLine();
				System.out.println("Titulo do livro: ");
				tituloLivro = input.nextLine();
				List<Long> idsCategorias = new ArrayList<Long>();
				int sair = 0;
				while (sair != 2) {
					System.out.println("Digite um ID de categoria: ");
					Long idCategoria = Long.parseLong(input.nextLine());
					idsCategorias.add(idCategoria);
					System.out.println("Deseja colocar mais alguma categoria ? s para sim e n para não: ");
					sair = Integer.parseInt(input.nextLine());
				}
				System.out.println("Descrição do livro: ");
				descricao = input.nextLine();
				System.out.println("preço do livro: ");
				preco = input.nextBigDecimal();
				System.out.println("Edições do livro: ");
				edicao = input.nextLine();
				System.out.println("Ano de lançamento do livro: ");
				anoLancamento = Integer.parseInt(input.nextLine());
				System.out.println("Id da Editora: ");
				idEditora = Long.parseLong(input.nextLine());

				List<Image> fotosLivro = new ArrayList<Image>();
				int sairImagem = 0;
				while (sairImagem != 2) {
					System.out.println("Digite o endereço da imagem: ");
					String edereçoDaImagem = input.nextLine();
					BufferedImage imagem = ImageIO.read(new File(edereçoDaImagem));
					fotosLivro.add(imagem);
					System.out.println("Deseja colocar mais alguma Imagem ? 1 para sim e 2 para não: ");
					sairImagem = Integer.parseInt(input.nextLine());
				}
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
				controllerLivro.cadastrarLivro(isbn, tituloLivro, idsCategorias, descricao, preco, edicao,
						anoLancamento, idEditora, fotosLivro, autores, quantidade);
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
				System.out.println("Edições do livro: ");
				edicao = input.nextLine();
				System.out.println("Ano de lançamento do livro: ");
				anoLancamento = Integer.parseInt(input.nextLine());
				System.out.println("Id da Editora: ");
				idEditora = Long.parseLong(input.nextLine());
				System.out.println("Digite a quantitade de livros em estoque: ");
				quantidade = Integer.parseInt(input.nextLine());

				controllerLivro.alterarLivro(isbn, tituloLivro, descricao, preco, edicao, anoLancamento, idEditora,
						quantidade);
				break;

			case 7:
				System.out.println("ISBN do livro para removelo: ");
				isbn = input.nextLine();
				controllerLivro.removerLivro(isbn);
				break;

			case 8:
				System.out.println(controllerLivro.listarCincoLivrosComMenorPreco().toString());
				break;
			case 9:
				// System.out.println(controllerLivro.listarLivros());
				break;
			case 10:
				System.out.println("Digite o Nome da categoria: ");
				nome = input.nextLine();
				controllerCategotia.criarCategoria(nome);
				break;

			case 11:
				System.out.println("ISBN do livro: ");
				isbn = input.nextLine();
				System.out.println("Email do cliente: ");
				email = input.nextLine();
				controllerUsuario.adcionarAoCarinho(isbn, email);
				break;
			case 12:

				System.out.println("Email do cliente: ");
				email = input.nextLine();
				List<String> isbns = new ArrayList<String>();
				int sairCompra = 0;
				while (sairCompra != 2) {
					System.out.println("ISBN do livro: ");
					isbn = input.nextLine();
					System.out.println("1- para continuar e 2 para finalizar: ");
					sairCompra = Integer.parseInt(input.nextLine());
				}
				controllerUsuario.comprarLivro(isbns, email);
				break;
			}
		}
	}
}
