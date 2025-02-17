package com.ifcolab.safesoft.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;
import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.ManyToMany;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import javax.persistence.JoinTable;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import com.ifcolab.safesoft.model.enums.StatusServico;
import javax.persistence.FetchType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Servico {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private LocalDateTime dataHora;
    
    private String observacoes;
    
    @Enumerated(EnumType.STRING)
    private StatusServico status;
    
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    
    @ManyToOne
    @JoinColumn(name = "tecnico_id")
    private Tecnico tecnico;
    
    @ManyToOne
    @JoinColumn(name = "Suporte_id")
    private Suporte Suporte;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "Servico_Item",
        joinColumns = @JoinColumn(name = "Servico_id"),
        inverseJoinColumns = @JoinColumn(name = "itens_id")
    )
    private List<Item> itens;
    
    @OneToOne(mappedBy = "servico")
    private Pagamento pagamento;

    
    public Servico(int id, LocalDateTime dataHora, String observacoes, StatusServico status,
                   Suporte Suporte, Tecnico tecnico, Cliente cliente,
                   List<Item> itens) {
        this.id = id;
        this.dataHora = dataHora;
        this.observacoes = observacoes;
        this.status = status;
        this.Suporte = Suporte;
        this.tecnico = tecnico;
        this.cliente = cliente;
        this.itens = itens;
    }
    
    public boolean isAgendada() {
        return StatusServico.AGENDADA.equals(status);
    }
    
    public boolean isConfirmada() {
        return StatusServico.CONFIRMADA.equals(status);
    }
    
    public boolean isCancelada() {
        return StatusServico.CANCELADA.equals(status);
    }
    
    public boolean isConcluida() {
        return StatusServico.CONCLUIDA.equals(status);
    }
    
    public double getValorTotal() {
        return itens.stream()
                .mapToDouble(Item::getValor)
                .sum();
    }
    
    @Override
    public String toString() {
        return String.format("Servico do dia %s - %s",
            dataHora.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")),
            itens.stream()
                .map(Item::getNome)
                .collect(Collectors.joining(", "))
        );
    }
}
