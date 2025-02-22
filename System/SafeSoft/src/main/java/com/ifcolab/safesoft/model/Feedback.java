package com.ifcolab.safesoft.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import java.time.LocalDateTime;
import javax.persistence.ManyToOne;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Feedback {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(nullable = false)
    private String titulo;
    
    @Column(nullable = false, length = 1000)
    private String descricao;
    
    @Column(name = "numAvaliacaoEstrela", nullable = false)
    private int avaliacao; // 1 a 5 estrelas
    
    @Column(nullable = false)
    private LocalDateTime dataAvaliacao;
    
    @ManyToOne
    @JoinColumn(name = "servico_id", nullable = false)
    private Servico servico;
    
    public Feedback(String titulo, String descricao, int avaliacao, Servico servico, LocalDateTime dataAvaliacao) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.avaliacao = avaliacao;
        this.servico = servico;
        this.dataAvaliacao = dataAvaliacao;
    }
}
