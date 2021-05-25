package com.example.Livraria.utilitarios;

public class AutenticacaoCPF {
	public static boolean autenticarCPF(String cpf) {
		String CPF = "";
		for (int i = 0; i < cpf.length(); i++) {
			CPF = cpf.replace(".", "");
		}
		String cpfFinal = null;
		for (int i = 0; i < CPF.length(); i++) {
			cpfFinal = CPF.replace("-", "");
		}
		System.out.println(cpfFinal);
		if (cpfFinal.equals("00000000000") || cpfFinal.equals("11111111111") || cpfFinal.equals("22222222222")
				|| cpfFinal.equals("33333333333") || cpfFinal.equals("44444444444") || cpfFinal.equals("55555555555")
				|| cpfFinal.equals("66666666666") || cpfFinal.equals("77777777777") || cpfFinal.equals("88888888888")
				|| cpfFinal.equals("99999999999")) {
			return false;
		}

		char dig10, dig11;
		int sm, i, r, num, peso;

		try {
			// Calculo do primeiro Digito Verificador
			sm = 0;
			peso = 10;
			for (i = 0; i < 9; i++) {
				num = (int) (cpfFinal.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}
			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11))
				dig10 = '0';
			else
				dig10 = (char) (r + 48);

			// Calculo do segundo Digito Verificador
			sm = 0;
			peso = 11;
			for (i = 0; i < 10; i++) {
				num = (int) (cpfFinal.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}
			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11))
				dig11 = '0';
			else
				dig11 = (char) (r + 48);

			if ((dig10 == cpfFinal.charAt(9)) && (dig11 == cpfFinal.charAt(10)))
				return (true);
			else
				return (false);
		} catch (Exception e) {
			return (false);
		}
	}
}
