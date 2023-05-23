package br.com.graficaplantao.rest.api.domain.transacoesEntrada.itensTransacoesEntrada.dto.request;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record AtualizacaoItemTransacaoEntradaDTO(

        @NotNull
        Long id,

        Long idMaterial,

        String undCom,

        BigDecimal quantCom,

        BigDecimal valorUntCom,

        BigDecimal valorIpi,

        String obs
) {
}
