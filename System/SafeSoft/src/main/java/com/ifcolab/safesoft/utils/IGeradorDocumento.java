package com.ifcolab.safesoft.utils;

import com.ifcolab.safesoft.model.Servico;
import com.ifcolab.safesoft.model.Pagamento;
import com.ifcolab.safesoft.model.Item;
import java.util.List;

public interface IGeradorDocumento {
    public void gerarDocumento(String caminho, String... conteudo);
    public void combinarDocumentos(String caminhoSaida, List<String> caminhosEntrada);
    public void gerarReciboPagamento(String caminho, Servico servico, Pagamento pagamento);
    public void gerarRelatorioItem(String caminho, Servico servico, Item item, String resultado, String observacoes);
} 