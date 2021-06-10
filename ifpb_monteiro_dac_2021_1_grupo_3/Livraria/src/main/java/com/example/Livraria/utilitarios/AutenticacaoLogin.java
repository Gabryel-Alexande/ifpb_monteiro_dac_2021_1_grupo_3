package com.example.Livraria.utilitarios;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.Livraria.exeception.LoginException;

public class AutenticacaoLogin {
	/**
	 * Valida uma senha forte e para isso a senha deve ter:
	 * Mais do que 6 caracteres; 
	 * Letras maiúsculas e minúsculas; 
	 * Números; 
	 * Caracteres especiais.
	 * A senha deve conter pelo menos um elemento de cada:
	 * 
	 * @param senha
	 * @return
	 */
	public static boolean validarrSenha(String senha) {
		if (senha.length() < 6 || senha.length() > 51)
			return false;

		boolean achouNumero = false;
		boolean achouMaiuscula = false;
		boolean achouMinuscula = false;
		boolean achouSimbolo = false;
		for (char c : senha.toCharArray()) {
			if (c >= '0' && c <= '9') {
				achouNumero = true;
			} else if (c >= 'A' && c <= 'Z') {
				achouMaiuscula = true;
			} else if (c >= 'a' && c <= 'z') {
				achouMinuscula = true;
			} else {
				achouSimbolo = true;
			}
		}
		return achouNumero && achouMaiuscula && achouMinuscula && achouSimbolo;
	}
	
	//este metodo sera chamado em tempo real, equanto o ussuario digita a senha repetida
	public void vereficarSenha(String senha1, String senha2) throws LoginException {
		if(!senha1.equals(senha2)) {
			throw new LoginException("[ERRO] As senhas devem ser iguais!");
		}
	}

	/**
	 * Verifica se o email tem os requisitos nescessarios ex: @email.com
	 */
	public static boolean validarLogin(String email) {
		// TODO Auto-generated method stub
		boolean isEmailIdValid = false;
		if (email != null && email.length() > 0) {
			String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
			Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(email);
			if (matcher.matches()) {
				isEmailIdValid = true;
			}
		}
		return isEmailIdValid;
	}
}
