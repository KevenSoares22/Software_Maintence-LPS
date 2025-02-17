package com.ifcolab.safesoft.utils;

import com.ifcolab.safesoft.model.Pessoa;
import org.apache.commons.mail.SimpleEmail;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class EmailNotificador implements Notificador {
    private static final String REMETENTE_EMAIL = "aryoproapp@gmail.com";
    private static final String SENHA_REMETENTE = "feeo ddby qcqa qwzv";

    @Override
    public boolean enviarNotificacao(Pessoa destinatario, String assunto, String conteudo) {
        CompletableFuture.runAsync(() -> {
            SimpleEmail email = new SimpleEmail();
            email.setHostName("smtp.gmail.com");
            email.setSmtpPort(465);
            email.setAuthentication(REMETENTE_EMAIL, SENHA_REMETENTE);
            email.setSSLOnConnect(true);

            try {
                email.setFrom(REMETENTE_EMAIL);
                email.setSubject(assunto);
                email.setMsg(conteudo);
                email.addTo(destinatario.getEmail());
                email.send();
                System.out.println("E-mail enviado com sucesso para: " + destinatario.getEmail());
            } catch (Exception e) {
                System.err.println("Erro ao enviar e-mail para " + destinatario.getEmail() + ": " + e.getMessage());
                e.printStackTrace();
            }
        }).orTimeout(1, TimeUnit.MINUTES);
        return true;
    }
}
