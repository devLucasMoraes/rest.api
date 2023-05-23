package br.com.graficaplantao.rest.api.domain.transacoesEntrada.itensTransacoesEntrada.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record NovoItemTransacaoEntradaDTO(

        @NotNull
        Long idMaterial,

        @NotBlank
        String undCom,

        @NotNull
        BigDecimal quantCom,

        @NotNull
        BigDecimal valorUntCom,

        @NotNull
        BigDecimal valorIpi,

        String obs
) {
}
