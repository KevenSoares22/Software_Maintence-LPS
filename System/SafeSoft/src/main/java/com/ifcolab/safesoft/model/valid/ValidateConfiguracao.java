package com.ifcolab.safesoft.model.valid;

import com.ifcolab.safesoft.model.ConfiguracaoSistema;
import com.ifcolab.safesoft.model.exceptions.ConfiguracaoException;
import java.time.LocalTime;

public class ValidateConfiguracao {
    
    public ConfiguracaoSistema validaCamposEntrada(LocalTime horarioAbertura, LocalTime horarioFechamento, int intervaloServicoMinutos, boolean funcionaSegunda, boolean funcionaTerca, boolean funcionaQuarta, boolean funcionaQuinta, boolean funcionaSexta, boolean funcionaSabado, boolean funcionaDomingo, int tempoMinimoAntecedenciaMinutos, int tempoMaximoAgendamentoDias) {

        if (horarioAbertura == null) {
            throw new ConfiguracaoException("Horário de abertura não pode estar em branco.");
        }
        
        if (horarioFechamento == null) {
            throw new ConfiguracaoException("Horário de fechamento não pode estar em branco.");
        }
        
        if (horarioFechamento.isBefore(horarioAbertura) || horarioFechamento.equals(horarioAbertura)) {
            throw new ConfiguracaoException("Horário de fechamento deve ser posterior ao horário de abertura.");
        }
        
        if (intervaloServicoMinutos < 15 || intervaloServicoMinutos > 120) {
            throw new ConfiguracaoException("Intervalo de servico deve estar entre 15 e 120 minutos.");
        }
        
        if (!funcionaSegunda && !funcionaTerca && !funcionaQuarta && 
            !funcionaQuinta && !funcionaSexta && !funcionaSabado && !funcionaDomingo) {
            throw new ConfiguracaoException("Pelo menos um dia da semana deve estar selecionado.");
        }
        
        if (tempoMinimoAntecedenciaMinutos < 0 || tempoMinimoAntecedenciaMinutos > 1440) {
            throw new ConfiguracaoException("Tempo mínimo de antecedência deve estar entre 0 e 24 horas.");
        }
        
        if (tempoMaximoAgendamentoDias < 1 || tempoMaximoAgendamentoDias > 365) {
            throw new ConfiguracaoException("Tempo máximo de agendamento deve estar entre 1 e 365 dias.");
        }
        
        return new ConfiguracaoSistema(1, horarioAbertura, horarioFechamento, intervaloServicoMinutos, funcionaSegunda, funcionaTerca, funcionaQuarta, funcionaQuinta, funcionaSexta, funcionaSabado, funcionaDomingo, tempoMinimoAntecedenciaMinutos, tempoMaximoAgendamentoDias);

    }

} 