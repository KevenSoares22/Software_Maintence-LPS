package com.ifcolab.safesoft.utils;

import com.ifcolab.safesoft.model.Pessoa;

public interface INotificador {
    boolean notificar(Pessoa pessoa, String titulo, String mensagem);
} 