package com.ifcolab.safesoft.model.enums;

public enum TipoItem {
    PLACA_MAE("Placa-mãe"),
    PROCESSADOR("Processador"),
    MEMORIA_RAM("Memória RAM"),
    HD_SSD("HD / SSD"),
    FONTE_ALIMENTACAO("Fonte de Alimentação"),
    PLACA_VIDEO("Placa de Vídeo"),
    COOLER("Cooler"),
    PASTA_TERMICA("Pasta Térmica"),
    MONITOR("Monitor"),
    TECLADO("Teclado"),
    MOUSE("Mouse"),
    IMPRESSORA("Impressora"),
    NOBREAK("Nobreak"),
    CABOS_CONECTORES("Cabos e Conectores"),
    LIMPEZA_INTERNA("Limpeza Interna"),
    TROCA_COMPONENTES("Troca de Componentes"),
    TESTE_DESEMPENHO("Teste de Desempenho"),
    ATUALIZACAO_SOFTWARE("Atualização de Software");

    private final String descricao;

    TipoItem(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
