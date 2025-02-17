package com.ifcolab.safesoft.model;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import com.ifcolab.safesoft.model.enums.TipoUsuario;
import com.ifcolab.safesoft.model.enums.TipoSexo;
import com.ifcolab.safesoft.model.enums.AreaGestao;

@Getter
@Setter
@Entity
public class Tecnico extends Pessoa {
    
    @Column(unique = true)
    private String cod;
    
    @Enumerated(EnumType.STRING)
    private AreaGestao areagestao;
    
    @OneToMany(mappedBy = "tecnico")
    private List<Servico> servicos;
    
    public Tecnico() {
        super();
    }
    
    public Tecnico(String nome, String email, String senha, String cpf, TipoSexo sexo,
                  String dataNascimento, String telefone, String endereco,
                  String cod, AreaGestao areagestao, int avatar) {
        super(nome, 
              email, 
              senha, 
              cpf, 
              sexo, 
              LocalDate.parse(dataNascimento, DateTimeFormatter.ofPattern("dd/MM/yyyy")), 
              telefone, 
              endereco, 
              TipoUsuario.TECNICO,
              avatar);  
        this.cod = cod;
        this.areagestao = areagestao;
    }
    
    @Override
    public String toString() {
        return this.getNome() + " (" + this.areagestao.getDescricao() + " )";
    }
}
