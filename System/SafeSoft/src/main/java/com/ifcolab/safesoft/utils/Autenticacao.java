package com.ifcolab.safesoft.utils;

import com.ifcolab.safesoft.model.Pessoa;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Autenticacao {
    private static Autenticacao instance;
    private Pessoa usuario;

    private Autenticacao() {}

    public static Autenticacao getInstance() {
        if (instance == null) {
            instance = new Autenticacao();
        }
        return instance;
    }
} 