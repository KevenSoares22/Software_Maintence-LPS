package com.ifcolab.safesoft.model.valid;

import com.ifcolab.safesoft.model.Tecnico;
import com.ifcolab.safesoft.model.exceptions.TecnicoException;
import com.ifcolab.safesoft.model.enums.AreaGestao;
import com.ifcolab.safesoft.model.enums.TipoSexo;

public class ValidateTecnico extends ValidatePessoa {
    
    public Tecnico validaCamposEntrada(String nome, String email, String senha, String cpf, TipoSexo sexo, String dataNascimento, String telefone, String endereco, String cod, AreaGestao especializacao, int avatar) {

        validarCamposBasicos(nome, email, senha, cpf, sexo, dataNascimento, telefone, endereco);
        
        if (cod == null || cod.isEmpty()) {
            throw new TecnicoException("cod não pode estar em branco.");
        }
        
        if (!isValidcod(cod)) {
            throw new TecnicoException("cod inválido.");
        }
        
        if (especializacao == null) {
            throw new TecnicoException("Especialização não pode estar em branco.");
        }

        return new Tecnico(nome, email, senha, cpf, sexo, dataNascimento, telefone, endereco, cod, especializacao, avatar);
    }
    
    private boolean isValidcod(String cod) {
        cod = cod.replaceAll("[^0-9]", "");
        return cod.length() >= 5 && cod.length() <= 6 && cod.matches("^\\d+$");
    }
}
