package com.ifcolab.safesoft.model.valid;

import com.ifcolab.safesoft.model.Cliente;
import com.ifcolab.safesoft.model.Suporte;
import com.ifcolab.safesoft.model.Tecnico;
import com.ifcolab.safesoft.model.Item;
import com.ifcolab.safesoft.model.exceptions.ServicoException;
import java.time.LocalDateTime;
import java.util.List;

public class ValidateServico {
    
    public void validaCamposEntrada(LocalDateTime dataHora, String observacoes,
                                    Cliente cliente, Tecnico tecnico, Suporte Suporte,
                                    List<Item> itens) {
        
        if (dataHora == null) {
            throw new ServicoException("Data e hora não podem estar em branco");
        }
        
        if (cliente == null) {
            throw new ServicoException("Cliente não selecionado");
        }
        
        if (tecnico == null) {
            throw new ServicoException("Tecnico não selecionado");
        }
        
        if (Suporte == null) {
            throw new ServicoException("Cliente não selecionada");
        }
        
        if (itens == null || itens.isEmpty()) {
            throw new ServicoException("Selecione pelo menos um item");
        }
        
        if (observacoes != null && observacoes.length() > 1000) {
            throw new ServicoException("Observações muito longas. Máximo de 1000 caracteres.");
        }
    }
    
    public void validarHorarioConflitante(LocalDateTime dataHoraServico, 
            LocalDateTime dataHoraExistente, int duracaoMinutos) {
            
        LocalDateTime fimServicoExistente = dataHoraExistente.plusMinutes(duracaoMinutos);
        
        if ((dataHoraServico.isEqual(dataHoraExistente) || 
             dataHoraServico.isAfter(dataHoraExistente)) && 
            dataHoraServico.isBefore(fimServicoExistente)) {
            throw new ServicoException("Existe conflito de horário com outra servico");
        }
    }
    
    public void validarHorarioFuncionamento(LocalDateTime dataHora, 
            LocalDateTime horarioAbertura, LocalDateTime horarioFechamento) {
            
        if (dataHora.isBefore(horarioAbertura) || dataHora.isAfter(horarioFechamento)) {
            throw new ServicoException("Horário fora do período de funcionamento");
        }
    }
    
    public void validarAntecedencia(LocalDateTime dataHora, 
            int tempoMinimoAntecedenciaMinutos, int tempoMaximoAgendamentoDias) {
            
        LocalDateTime agora = LocalDateTime.now();
        long minutosAteServico = java.time.Duration.between(agora, dataHora).toMinutes();
        long diasAteServico = java.time.Duration.between(agora, dataHora).toDays();
        
        if (minutosAteServico < tempoMinimoAntecedenciaMinutos) {
            throw new ServicoException("É necessário agendar com no mínimo " +
                tempoMinimoAntecedenciaMinutos + " minutos de antecedência");
        }
        
        if (diasAteServico > tempoMaximoAgendamentoDias) {
            throw new ServicoException("Não é possível agendar servicos com mais de " +
                tempoMaximoAgendamentoDias + " dias de antecedência");
        }
    }
}
