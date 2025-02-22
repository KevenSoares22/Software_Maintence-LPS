package com.ifcolab.safesoft.model;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Relatorio {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private LocalDateTime dataEmissao;
    
    @Column(length = 1000)
    private String resultado;
    
    @Column(length = 500)
    private String observacoes;

    private String caminhoPdf;
    
    @OneToOne
    @JoinColumn(name = "servico_id")
    private Servico servico;
}
