package br.com.serratec.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
public class EmailConfig {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String para, String assunto, String texto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("habner.martins@aluno.senai.br");
        message.setTo(para);
        message.setSubject(assunto);
        message.setText("Dados do cadastro do usuário:\n" + texto + "\n\n\n\n" + "Cadastrado com sucesso!  :)");
        javaMailSender.send(message);

    }

}