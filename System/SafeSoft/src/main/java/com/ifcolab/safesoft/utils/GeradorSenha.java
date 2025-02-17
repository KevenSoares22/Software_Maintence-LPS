package com.ifcolab.safesoft.utils;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GeradorSenha {
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String SPECIALS = "!@#$%^&*";
    private static final String ALL_CHARS = UPPERCASE + LOWERCASE + DIGITS + SPECIALS;

    public static String gerarSenha(int tamanho) {
        if (tamanho < 4) {
            throw new IllegalArgumentException("O tamanho da senha deve ser pelo menos 4 caracteres.");
        }
        
        SecureRandom random = new SecureRandom();
        List<Character> senha = new ArrayList<>();
        
        senha.add(UPPERCASE.charAt(random.nextInt(UPPERCASE.length())));
        senha.add(LOWERCASE.charAt(random.nextInt(LOWERCASE.length())));
        senha.add(DIGITS.charAt(random.nextInt(DIGITS.length())));
        senha.add(SPECIALS.charAt(random.nextInt(SPECIALS.length())));
        
        for (int i = 4; i < tamanho; i++) {
            senha.add(ALL_CHARS.charAt(random.nextInt(ALL_CHARS.length())));
        }
        
        Collections.shuffle(senha, random);
        
        StringBuilder senhaFinal = new StringBuilder();
        for (char c : senha) {
            senhaFinal.append(c);
        }
        
        return senhaFinal.toString();
    }
}
