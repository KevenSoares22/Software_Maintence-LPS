package com.ifcolab.safesoft.model.valid;

import com.ifcolab.safesoft.model.Cliente;
import com.ifcolab.safesoft.model.enums.TipoSexo;

public class ValidateCliente extends ValidatePessoa {
    
    public Cliente validaCamposEntrada(String nome, String email, String senha, String cpf, TipoSexo sexo, String dataNascimento, String telefone, String endereco, String historicoTecnico, int avatar) {
        validarCamposBasicos(nome, email, senha, cpf, sexo, dataNascimento, telefone, endereco);
        
        return new Cliente(nome, email, senha, cpf, sexo, dataNascimento, telefone, endereco, historicoTecnico, avatar);
    }
}

