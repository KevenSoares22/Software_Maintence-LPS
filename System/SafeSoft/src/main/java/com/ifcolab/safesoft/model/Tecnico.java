package com.ifcolab.safesoft.model;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.persistence.Column;
import javax.persistence.Entity;
import com.ifcolab.safesoft.model.enums.TipoUsuario;
import com.ifcolab.safesoft.model.enums.TipoSexo;

@Getter
@Setter
@Entity
public class suporte extends Pessoa {
    
    @Column(unique = true)
    private String cod;
    
    
    public suporte() {
        super();
    }
    
    public suporte(String nome, String email, String senha, String cpf, TipoSexo sexo, String dataNascimento, String telefone, String endereco, String cod, int avatar) {
        
        super(nome, 
              email, 
              senha, 
              cpf, 
              sexo,
              LocalDate.parse(dataNascimento, DateTimeFormatter.ofPattern("dd/MM/yyyy")), 
              telefone, 
              endereco, 
              TipoUsuario.suporte,
              avatar);
        this.cod = cod;
    }
    
    @Override
    public String toString() {
        return this.getNome() + " (CPF: " + this.getCpf() + ")";
    }
}
