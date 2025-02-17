package com.ifcolab.safesoft.model.valid;

import com.ifcolab.safesoft.model.Pagamento;
import com.ifcolab.safesoft.model.Servico;
import com.ifcolab.safesoft.model.Pessoa;
import com.ifcolab.safesoft.model.exceptions.PagamentoException;
import com.ifcolab.safesoft.model.enums.StatusPagamento;
import com.ifcolab.safesoft.model.enums.MetodoPagamento;
import java.time.LocalDateTime;

public class ValidatePagamento {
    
    public Pagamento validaCamposEntrada(double valor, StatusPagamento status, MetodoPagamento metodoPagamento, String detalhes, Servico servico, Pessoa registrador) {
        if (valor <= 0) {
            throw new PagamentoException("Valor deve ser maior que zero.");
        }
        
        if (status == null) {
            throw new PagamentoException("Status do pagamento não pode estar em branco.");
        }
        
        if (metodoPagamento == null) {
            throw new PagamentoException("Método de pagamento não pode estar em branco.");
        }
        
        if (detalhes != null && detalhes.length() > 500) {
            throw new PagamentoException("Detalhes não podem ter mais que 500 caracteres.");
        }
        
        if (registrador == null) {
            throw new PagamentoException("Registrador do pagamento não pode estar em branco.");
        }
        
        return new Pagamento(0, valor, status, metodoPagamento, LocalDateTime.now(), 
                           detalhes, servico, registrador);
    }
}
