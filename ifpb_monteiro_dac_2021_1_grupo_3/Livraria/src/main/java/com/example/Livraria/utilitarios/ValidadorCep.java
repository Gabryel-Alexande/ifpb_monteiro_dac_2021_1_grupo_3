package com.example.Livraria.utilitarios;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidadorCep {
	public static boolean ValidaCep(String cep) {
		if (cep.length() == 8) {
			cep = cep.substring(0, 5) + "-" + cep.substring(5, 3);
			// txt.Text = cep;
		}
		String expression = "[0-9]{5}-[0-9]{3}";
		Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(cep);
		return matcher.matches();
	}
}
