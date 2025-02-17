package com.ifcolab.safesoft.model.enums;

public enum AreaGestao {
    MANUTENCAO_PREVENTIVA("Manutenção Preventiva"),
    MANUTENCAO_CORRETIVA("Manutenção Corretiva"),
    MANUTENCAO_PREDITIVA("Manutenção Preditiva"),
    GESTAO_DE_ATIVOS("Gestão de Ativos"),
    SUPORTE_Cliente("Suporte Técnico");

    private final String descricao;

    AreaGestao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
