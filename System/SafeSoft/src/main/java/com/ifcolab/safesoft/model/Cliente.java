package com.ifcolab.safesoft.model;

import lombok.Getter;
import lombok.Setter;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import com.ifcolab.safesoft.model.enums.TipoUsuario;
import com.ifcolab.safesoft.model.enums.TipoSexo;

@Getter
@Setter
@Entity
public class Cliente extends Pessoa {
    @Column(length = 1000)
    private String historicoTecnico;
    
    @OneToMany(mappedBy = "cliente")
    private List<Servico> servicos;
    
    public Cliente() {
        super();
    }
    
    public Cliente(String nome, String email, String senha, String cpf, TipoSexo sexo, String dataNascimento, String telefone, String endereco, String historicoTecnico, int avatar) {
        super(nome, 
              email, 
              senha, 
              cpf, 
              sexo, 
              LocalDate.parse(dataNascimento, DateTimeFormatter.ofPattern("dd/MM/yyyy")), 
              telefone, 
              endereco, 
              TipoUsuario.CLIENTE,
              avatar);
        this.historicoTecnico = historicoTecnico;
    }
    
    @Override
    public String toString() {
        return this.getNome();
    }
}
