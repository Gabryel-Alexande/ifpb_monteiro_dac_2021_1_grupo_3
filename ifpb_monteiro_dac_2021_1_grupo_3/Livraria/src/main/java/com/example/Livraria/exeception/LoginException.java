package com.example.Livraria.exeception;

@SuppressWarnings("serial")
public class LoginException extends Exception {
	public LoginException(String messagem) {
		super(messagem);
	}
}
