package com.example.Livraria;

import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.Livraria.controllers.ControllerUsuario;

@SpringBootApplication
public class LivrariaApplication {

	public static void main(String[] args) {
		SpringApplication.run(LivrariaApplication.class, args);
	}
	
	public void run (String ... args)throws Exception{
		boolean con  = true ; 
		ControllerUsuario controllerUsuario = new ControllerUsuario();
		Scanner input = new Scanner(System.in);
		String email;
		
		while(con) {
			
			System.out.println("0- Finalizar"
					+"\n1 -Registrar Novo Usuário"
					+"\n2 -Consultar Usuário pelo E-Mail"
					+"\n3- Cadastrar Autor "
					+"\n4 -Alterar Autor"
					+"\n5 -Cadastrar Livro"
					+"\n6 -Alterar Livro"
					+"\n7 -Excluir Livro"
					+"\n8 -Consultar os 5 mais baratos"
					+"\n9 -Consultar todos os Livros"
					+"\n10 -Criar categoria"
					+"\n11 - Adicionar o Livro ao Carrinho(pelo id)"
					);
					
					int opcao = Integer.parseInt(input.nextLine());
					switch(opcao){
						case 1:
						
							System.out.println("Nome:");
							String nome = input.nextLine();
							System.out.println("Email:");
								email = input.nextLine();
							System.out.println("Senha:");
							String senha = input.nextLine();
							System.out.println("CPF:");
							String cpf = input.nextLine();
							System.out.println("Você é Administrador ? (1 = Sim: 2= Não");
							int opadm = Integer.parseInt(input.nextLine());
							
							boolean adm = true;
							if (opadm==2)
								adm = false;
							
							
							try {
								controllerUsuario.cadatrarUsusario(cpf, nome, email, senha, adm);
								System.out.println("Cadastrado com Sucesso !!");
							}catch(Exception e) {
								System.out.println(e.getMessage());
							}
						case 2:
							System.out.println("Digite o Email:");
							 	email = input.nextLine(); 
							 	Object usuario =  controllerUsuario.consultarPorEmail(email);
							 	
							 	System.out.println(usuario.toString());
							 	
							 	
						
							
							
							
							
					}
						
			
		}
	}

}
