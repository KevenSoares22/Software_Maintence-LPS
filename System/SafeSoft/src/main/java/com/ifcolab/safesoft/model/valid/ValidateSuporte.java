package com.ifcolab.safesoft.model.valid;

import com.ifcolab.safesoft.model.Suporte;
import com.ifcolab.safesoft.model.enums.TipoSexo;
import com.ifcolab.safesoft.model.exceptions.SuporteException;


public class ValidateSuporte extends ValidatePessoa {

    public Suporte validaCamposEntrada(String nome, String email, String senha, String cpf, TipoSexo sexo, String dataNascimento, String telefone, String endereco, String cod, int avatar) {

        validarCamposBasicos(nome, email, senha, cpf, sexo, dataNascimento, telefone, endereco);
        
        if (cod == null || cod.isEmpty()) {
            throw new SuporteException("cod não pode estar em branco.");
        }
        
        if (!isValidcod(cod)) {
            throw new SuporteException("cod inválido.");
        }

        return new Suporte(nome, email, senha, cpf, sexo, dataNascimento, telefone, endereco, cod, avatar);
    }
    
    private boolean isValidcod(String cod) {
        cod = cod.replaceAll("[^0-9]", "");
        return cod.length() >= 5 && cod.length() <= 7 && cod.matches("^\\d+$");
    }
}
