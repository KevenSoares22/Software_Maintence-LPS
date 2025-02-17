package com.ifcolab.safesoft.model.valid;

import com.ifcolab.safesoft.model.Relatorio;
import com.ifcolab.safesoft.model.Servico;
import com.ifcolab.safesoft.model.exceptions.RelatorioException;
import java.time.LocalDateTime;

public class ValidateRelatorio {
    
    public Relatorio validaCamposEntrada(LocalDateTime dataEmissao, String resultado, String observacoes, String caminhoPdf, Servico servico) {
        if (dataEmissao == null) {
            throw new RelatorioException("Data de emissão não pode estar em branco.");
        }
        
        if (resultado == null || resultado.trim().isEmpty()) {
            throw new RelatorioException("Resultado não pode estar em branco.");
        }
        
        if (resultado.length() > 1000) {
            throw new RelatorioException("Resultado não pode ter mais que 1000 caracteres.");
        }
        
        if (observacoes != null && observacoes.length() > 500) {
            throw new RelatorioException("Observações não podem ter mais que 500 caracteres.");
        }
        
        if (caminhoPdf == null || caminhoPdf.trim().isEmpty()) {
            throw new RelatorioException("Caminho do PDF não pode estar em branco.");
        }
        
        if (servico == null) {
            throw new RelatorioException("Servico não pode estar em branco.");
        }
        
        return new Relatorio(0, dataEmissao, resultado, observacoes, caminhoPdf, servico);
    }
}