package com.example.Livraria.utilitarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EnviadorDeEmail {
	@Autowired
	private  JavaMailSender javaMailSender;
	
	public void enviarEmail(String email, String title, String mensagem) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(email);
		msg.setSubject(title);
		msg.setText(mensagem);
		javaMailSender.send(msg);
	}
}
