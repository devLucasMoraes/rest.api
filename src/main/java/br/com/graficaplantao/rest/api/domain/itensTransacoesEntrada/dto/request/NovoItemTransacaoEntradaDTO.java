package br.com.graficaplantao.rest.api.domain.itensTransacoesEntrada.dto.request;

import br.com.graficaplantao.rest.api.domain.categorias.Unidade;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record NovoItemTransacaoEntradaDTO(

        @NotNull
        Long idMaterial,

        @NotNull
        Unidade undCom,

        @NotNull
        BigDecimal quantCom,

        @NotNull
        BigDecimal valorUntCom,

        @NotNull
        BigDecimal valorIpi,

        String obs
) {
}
