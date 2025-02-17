package com.ifcolab.safesoft.controller;

import com.ifcolab.safesoft.controller.tablemodel.TMViewServico;
import com.ifcolab.safesoft.controller.tablemodel.TMViewMeusServicos;
import com.ifcolab.safesoft.controller.tablemodel.TMViewHistoricoItem;
import com.ifcolab.safesoft.model.*;
import com.ifcolab.safesoft.model.Cliente;
import com.ifcolab.safesoft.model.dao.ServicoDAO;
import com.ifcolab.safesoft.model.dao.ConfiguracaoSistemaDAO;
import com.ifcolab.safesoft.model.exceptions.ServicoException;
import com.ifcolab.safesoft.model.valid.ValidateServico;
import com.ifcolab.safesoft.model.enums.StatusServico;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.ArrayList;
import javax.swing.JTable;

public class ServicoController {

    private final ServicoDAO repositorio;
    private final ConfiguracaoSistemaDAO configDAO;
    private final ValidateServico validador;
    
    public ServicoController() {
        this.repositorio = new ServicoDAO();
        this.configDAO = new ConfiguracaoSistemaDAO();
        this.validador = new ValidateServico();
    }
    
    public ConfiguracaoSistema getConfiguracao() {
        ConfiguracaoSistema config = configDAO.getConfiguracao();
        if (config == null) {
            throw new ServicoException("Erro ao carregar configurações do sistema");
        }
        return config;
    }
    
    public void cadastrar(LocalDateTime dataHora, String observacoes,
                          Cliente cliente, Tecnico tecnico, Suporte Suporte,
                          List<Item> itens) {
            
        validador.validaCamposEntrada(dataHora, observacoes, cliente, tecnico,
                Suporte, itens);
            
        ConfiguracaoSistema config = configDAO.getConfiguracao();
        
        validador.validarHorarioFuncionamento(dataHora, 
            LocalDateTime.of(dataHora.toLocalDate(), config.getHorarioAbertura()),
            LocalDateTime.of(dataHora.toLocalDate(), config.getHorarioFechamento()));
            
        validador.validarAntecedencia(dataHora, 
            config.getTempoMinimoAntecedenciaMinutos(),
            config.getTempoMaximoAgendamentoDias());
            
        List<Servico>servicosExistentes = repositorio.buscarServicosNoPeriodo(
            dataHora, dataHora.plusMinutes(config.getIntervaloServicoMinutos()));
            
        for  (Servico servico : servicosExistentes) {
            validador.validarHorarioConflitante(dataHora, 
                servico.getDataHora(),
                config.getIntervaloServicoMinutos());
        }
        
         Servico servico = new Servico(0, dataHora, observacoes,
            StatusServico.AGENDADA, Suporte, tecnico, cliente, itens);
            
        repositorio.save(servico);
    }
    
    public void atualizar(int id, LocalDateTime dataHora, String observacoes,
                          Cliente cliente, Tecnico tecnico, Suporte Suporte,
                          List<Item> itens) {
            
        
        validador.validaCamposEntrada(dataHora, observacoes, cliente, tecnico,
                Suporte, itens);
            
        
         Servico servicoAtual = repositorio.find(id);
        if (servicoAtual == null) {
            throw new ServicoException("Servico não encontrado");
        }
        
        ConfiguracaoSistema config = configDAO.getConfiguracao();
        
        validador.validarHorarioFuncionamento(dataHora, 
            LocalDateTime.of(dataHora.toLocalDate(), config.getHorarioAbertura()),
            LocalDateTime.of(dataHora.toLocalDate(), config.getHorarioFechamento()));
            
        validador.validarAntecedencia(dataHora, 
            config.getTempoMinimoAntecedenciaMinutos(),
            config.getTempoMaximoAgendamentoDias());
            
        List<Servico> servicosExistentes = repositorio.buscarServicosNoPeriodo(
            dataHora, dataHora.plusMinutes(config.getIntervaloServicoMinutos()));
            
        for  (Servico servico : servicosExistentes) {
            if (servico.getId() != id) {
                validador.validarHorarioConflitante(dataHora, 
                    servico.getDataHora(),
                    config.getIntervaloServicoMinutos());
            }
        }
        
         Servico servico = new Servico(id, dataHora, observacoes,
            servicoAtual.getStatus(), Suporte, tecnico, cliente, itens);
            
        repositorio.update(servico);
    }
    
    public void excluir(int id) {
        repositorio.delete(id);
    }
    
    public List<Servico>findAll() {
        return repositorio.findAll();
    }
    
    public void atualizarTabela(JTable grd) {
        TMViewServico tmServico = new TMViewServico(repositorio.findAll());
        grd.setModel(tmServico);
    }
    
    public List <Servico> buscarPorData(LocalDate data) {
        if (data == null) {
            throw new ServicoException("Data não pode ser nula");
        }
        return repositorio.buscarPorData(data);
    }
    
    public List <Servico> buscarServicosPorCliente(int idCliente) {
        return repositorio.buscarServicosPorCliente(idCliente);
    }
    
    public void atualizarTabelaMeusServicos(JTable grd, int idCliente) {
        TMViewMeusServicos tmMeusServicos = new TMViewMeusServicos(
            repositorio.buscarServicosPorCliente(idCliente)
        );
        grd.setModel(tmMeusServicos);
    }
    
    public void confirmarServico(int id) {
        Servico servico = repositorio.find(id);
        if (servico == null) {
            throw new ServicoException("Servico não encontrada");
        }
        
        if (!servico.isAgendada()) {
            throw new ServicoException("Apenas servicos agendadas podem ser confirmadas");
        }
        
        servico.setStatus(StatusServico.CONFIRMADA);
        repositorio.update(servico);
    }
    
    public void cancelarServico(int id) {
         Servico servico = repositorio.find(id);
        if (servico == null) {
            throw new ServicoException("Servico não encontrada");
        }
        
        if (!servico.isAgendada()) {
            throw new ServicoException("Apenas servicos agendadas podem ser canceladas");
        }
        
        servico.setStatus(StatusServico.CANCELADA);
        repositorio.update(servico);
    }
    
    public void realizarServico(int id) {
         Servico servico = repositorio.find(id);
        if (servico == null) {
            throw new ServicoException("Servico não encontrado");
        }
        
        if (!servico.isConfirmada()) {
            throw new ServicoException("Apenas servicos confirmados podem ser realizados");
        }
        
        servico.setStatus(StatusServico.CONCLUIDA);
        repositorio.update(servico);
    }
    
    public void atualizarTabelaHistorico(JTable grd, int idCliente) {
        List <Servico> servicos = buscarServicosPorCliente(idCliente);
        TMViewHistoricoItem tmHistorico = new TMViewHistoricoItem(servicos);
        grd.setModel(tmHistorico);
    }
    
    public Map<LocalTime, List <Servico>> getServicosOrganizadasPorHorario(LocalDate data) {
        ConfiguracaoSistema config = getConfiguracao();
        List <Servico> servicos = buscarPorData(data);
        
        Map<LocalTime, List <Servico>> servicosPorHorario = new TreeMap<>();
        
        LocalTime hora = config.getHorarioAbertura();
        while (!hora.isAfter(config.getHorarioFechamento())) {
            servicosPorHorario.put(hora, new ArrayList<>());
            hora = hora.plusMinutes(config.getIntervaloServicoMinutos());
        }
        
        for  (Servico servico : servicos) {
            LocalTime horaServico = servico.getDataHora().toLocalTime();
            servicosPorHorario.computeIfAbsent(horaServico, k -> new ArrayList<>())
                              .add(servico);
        }
        
        return servicosPorHorario;
    }
    
    // Métodos para DlgOpcoe Servico

    public boolean podeConfirmarServico(Servico servico) {
        return servico != null && servico.isAgendada();
    }

    public boolean podeCancelarServico(Servico servico) {
        return servico != null && servico.isAgendada();
    }

    public boolean podeRealizarServico(Servico servico) {
        return servico != null && servico.isConfirmada();
    }

    public void validarHorarioServico(LocalDateTime dataHora) {
        ConfiguracaoSistema config = getConfiguracao();
        
        if (!config.isDiaFuncionamento(dataHora.getDayOfWeek())) {
            throw new ServicoException("A clínica não funciona neste dia da semana");
        }
        
        LocalTime horario = dataHora.toLocalTime();
        if (horario.isBefore(config.getHorarioAbertura()) || 
            horario.isAfter(config.getHorarioFechamento())) {
            throw new ServicoException("Horário fora do período de funcionamento");
        }
        
        if (horario.getMinute() % config.getIntervaloServicoMinutos() != 0) {
            throw new ServicoException("Horário inválido para agendamento");
        }
        
        LocalDateTime agora = LocalDateTime.now();
        long minutosAteServico = java.time.Duration.between(agora, dataHora).toMinutes();
        long diasAteServico = java.time.Duration.between(agora, dataHora).toDays();
        
        if (minutosAteServico < config.getTempoMinimoAntecedenciaMinutos()) {
            throw new ServicoException("Antecedência mínima não respeitada");
        }
        
        if (diasAteServico > config.getTempoMaximoAgendamentoDias()) {
            throw new ServicoException("Prazo máximo para agendamento excedido");
        }
    }
}
