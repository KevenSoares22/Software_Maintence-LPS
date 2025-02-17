package com.ifcolab.safesoft.controller;

import com.ifcolab.safesoft.controller.tablemodel.TMViewItem;
import com.ifcolab.safesoft.model.Item;
import com.ifcolab.safesoft.model.dao.ItemDAO;
import com.ifcolab.safesoft.model.exceptions.ItemException;
import com.ifcolab.safesoft.model.valid.ValidateItem;
import com.ifcolab.safesoft.model.enums.TipoItem;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import javax.swing.JTable;

public class ItemController {

    private ItemDAO repositorio;

    public ItemController() {
        repositorio = new ItemDAO();
    }

    public void cadastrar(String descricao, String duracao, String valor, String requisitos, String contraindicacoes, TipoItem tipo, int intervaloRetornoDias) {
        ValidateItem valid = new ValidateItem();
        Item item = valid.validaCamposEntrada(descricao, duracao, valor, requisitos, contraindicacoes, tipo, intervaloRetornoDias);

        repositorio.save(item);
    }

    public void atualizar(int id, String descricao, String duracao, String valor, String requisitos, String contraindicacoes, TipoItem tipo, int intervaloRetornoDias) {
        ValidateItem valid = new ValidateItem();
        Item item = valid.validaCamposEntrada(descricao, duracao, valor, requisitos, contraindicacoes, tipo, intervaloRetornoDias);

        item.setId(id);

        repositorio.update(item);
    }

    public void excluir(Item item) {
        if (item == null) {
            throw new ItemException("Erro - Item inexistente.");
        }

        boolean deletado = repositorio.delete(item.getId());
        if (!deletado) {
            throw new ItemException("Erro - Item inexistente.");
        }
    }

    public List<Item> findAll() {
        return repositorio.findAll();
    }

    public void atualizarTabela(JTable grd) {
        TMViewItem tmItem = new TMViewItem(repositorio.findAll());
        grd.setModel(tmItem);
    }

    public void filtrarTabela(JTable grd, String descricao) {
        TMViewItem tmItem = new TMViewItem(repositorio.filterByDescricao(descricao));
        grd.setModel(tmItem);
    }

    public void filtrarTabelaPorCliente(JTable grd, int clienteId) {
        TMViewItem tmItem = new TMViewItem(repositorio.findByCliente(clienteId));
        grd.setModel(tmItem);
    }

    public void filtrarTabelaPorEquipe(JTable grd, int tecnicoId, int suporteId) {
        TMViewItem tmItem = new TMViewItem(repositorio.findByEquipe(tecnicoId, suporteId));
        grd.setModel(tmItem);
    }

    public void filtrarTabelaPorPeriodo(JTable grd, LocalDateTime inicio, LocalDateTime fim) {
        TMViewItem tmItem = new TMViewItem(repositorio.findByPeriodo(inicio, fim));
        grd.setModel(tmItem);
    }

    private Duration parseDuracao(String tempo) {
        if (tempo == null || !tempo.matches("\\d{1,2}:\\d{2}")) {
            throw new IllegalArgumentException("Formato inválido para tempo. Use HH:mm");
        }
        String[] partes = tempo.split(":");
        return Duration.ofHours(Integer.parseInt(partes[0])).plusMinutes(Integer.parseInt(partes[1]));
    }


}
