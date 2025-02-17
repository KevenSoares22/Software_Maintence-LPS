package com.ifcolab.safesoft.model;

import java.time.Duration;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import com.ifcolab.safesoft.model.enums.TipoItem;

@Getter
@Setter
@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private TipoItem tipo;

    @Column(length = 1000)
    private String descricao;

    private Duration tempoEstimadoManutencao;
    private double custo;
    private double valor;  // Novo campo

    @Column(length = 500)
    private String especificacoesTecnicas;

    @Column(length = 500)
    private String precaucoes;

    private int tempoGarantiaDias;

    public Item() {
    }

    public Item(TipoItem tipo, String descricao, String tempo,
                double custo, double valor, String especificacoesTecnicas, String precaucoes,
                int tempoGarantiaDias) {
        this.tipo = tipo;
        this.descricao = descricao;
        this.tempoEstimadoManutencao = parseDuracao(tempo);
        this.custo = custo;
        this.valor = valor;  // Inicializando o novo campo
        this.especificacoesTecnicas = especificacoesTecnicas;
        this.precaucoes = precaucoes;
        this.tempoGarantiaDias = tempoGarantiaDias;
    }

    private Duration parseDuracao(String tempo) {
        // Formato esperado: "HH:mm"
        String[] partes = tempo.split(":");
        int horas = Integer.parseInt(partes[0]);
        int minutos = Integer.parseInt(partes[1]);
        return Duration.ofHours(horas).plusMinutes(minutos);
    }

    @Override
    public String toString() {
        return this.getDescricao();
    }

    public String getNome() {
        return this.tipo.toString();
    }
}
