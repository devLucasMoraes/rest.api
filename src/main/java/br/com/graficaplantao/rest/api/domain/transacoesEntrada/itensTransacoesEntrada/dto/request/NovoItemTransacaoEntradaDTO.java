package br.com.graficaplantao.rest.api.domain.transacoesEntrada.itensTransacoesEntrada.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record NovoItemTransacaoEntradaDTO(

        @NotNull
        Long materiais_id,

        @NotBlank
        String und_com,

        @NotNull
        BigDecimal quant_com,

        @NotNull
        BigDecimal valor_unt_com,

        @NotNull
        BigDecimal valor_ipi,

        String obs
) {
}
