package com.example.Livraria.utilitarios;

public class ValidadorNome {
	public static boolean validarNome(String nome) {
		return nome.length() < 51 && nome.length() > 4;
	}
}
