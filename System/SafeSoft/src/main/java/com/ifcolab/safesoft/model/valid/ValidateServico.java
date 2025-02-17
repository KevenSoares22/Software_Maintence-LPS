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
    
    public void validarHorarioConflitante(LocalDateTime dataHoraConsulta, 
            LocalDateTime dataHoraExistente, int duracaoMinutos) {
            
        LocalDateTime fimConsultaExistente = dataHoraExistente.plusMinutes(duracaoMinutos);
        
        if ((dataHoraConsulta.isEqual(dataHoraExistente) || 
             dataHoraConsulta.isAfter(dataHoraExistente)) && 
            dataHoraConsulta.isBefore(fimConsultaExistente)) {
            throw new ServicoException("Existe conflito de horário com outra consulta");
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
        long minutosAteConsulta = java.time.Duration.between(agora, dataHora).toMinutes();
        long diasAteConsulta = java.time.Duration.between(agora, dataHora).toDays();
        
        if (minutosAteConsulta < tempoMinimoAntecedenciaMinutos) {
            throw new ServicoException("É necessário agendar com no mínimo " +
                tempoMinimoAntecedenciaMinutos + " minutos de antecedência");
        }
        
        if (diasAteConsulta > tempoMaximoAgendamentoDias) {
            throw new ServicoException("Não é possível agendar consultas com mais de " +
                tempoMaximoAgendamentoDias + " dias de antecedência");
        }
    }
}
