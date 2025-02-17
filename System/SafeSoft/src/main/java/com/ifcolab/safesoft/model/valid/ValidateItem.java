package com.ifcolab.safesoft.model.valid;

import com.ifcolab.safesoft.model.Item;
import com.ifcolab.safesoft.model.exceptions.ItemException;
import com.ifcolab.safesoft.model.enums.TipoItem;
import java.math.BigDecimal;
import java.time.Duration;

public class ValidateItem {

    public Item validaCamposEntrada(String descricao, String duracao, String valor, String requisitos, String contraindicacoes, TipoItem tipo, int intervaloRetornoDias) {
        if (descricao == null || descricao.isEmpty()) {
            throw new ItemException("Descrição não pode estar em branco.");
        }

        if (descricao.length() > 1000) {
            throw new ItemException("Descrição muito longa. Máximo de 1000 caracteres.");
        }

        if (duracao == null || duracao.isEmpty()) {
            throw new ItemException("Duração não pode estar em branco.");
        }

        isValidDuracao(duracao);

        BigDecimal valorNumerico = parseCusto(valor);

        if (requisitos != null && requisitos.length() > 500) {
            throw new ItemException("Requisitos muito longos. Máximo de 500 caracteres.");
        }

        if (contraindicacoes != null && contraindicacoes.length() > 500) {
            throw new ItemException("Contraindicações muito longas. Máximo de 500 caracteres.");
        }

        // Passando os parâmetros adicionais
        return new Item(
                tipo,
                descricao,
                duracao,
                valorNumerico.doubleValue(),
                0.0, // Custo padrão (ou altere conforme necessário)
                requisitos != null ? requisitos : "", // Especificações técnicas (valor default caso seja nulo)
                contraindicacoes != null ? contraindicacoes : "", // Precauções (valor default caso seja nulo)
                intervaloRetornoDias // Tempo de garantia (valor do parâmetro)
        );
    }

    private void isValidDuracao(String duracao) {
        if (!duracao.matches("^([0-9]{1,2}):([0-5][0-9])$")) {
            throw new ItemException("Duração inválida. Use o formato HH:mm");
        }

        String[] partes = duracao.split(":");
        int horas = Integer.parseInt(partes[0]);
        int minutos = Integer.parseInt(partes[1]);

        if (horas == 0 && minutos == 0) {
            throw new ItemException("Duração deve ser maior que zero.");
        }

        if (horas > 12) {
            throw new ItemException("Duração máxima permitida é 12 horas.");
        }
    }

    private BigDecimal parseCusto(String valor) {
        try {
            return new BigDecimal(valor.replace("R$", "").trim().replace(",", "."));
        } catch (NumberFormatException e) {
            throw new ItemException("Valor inválido. Use o formato: R$ 0,00");
        }
    }
}
