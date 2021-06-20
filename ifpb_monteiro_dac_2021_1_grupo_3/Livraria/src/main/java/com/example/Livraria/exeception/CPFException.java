package com.example.Livraria.exeception;

@SuppressWarnings("serial")
public class CPFException extends Exception{
	public CPFException() {
		super("CPF Invalido!");
	}
}
