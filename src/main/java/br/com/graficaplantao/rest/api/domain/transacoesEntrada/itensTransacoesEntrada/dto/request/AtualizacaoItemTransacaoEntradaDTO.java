package br.com.graficaplantao.rest.api.domain.transacoesEntrada.itensTransacoesEntrada.dto.request;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record AtualizacaoItemTransacaoEntradaDTO(

        @NotNull
        Long id,

        Long materiais_id,

        String und_com,

        BigDecimal quant_com,

        BigDecimal valor_unt_com,

        BigDecimal valor_ipi,

        String obs
) {
}
