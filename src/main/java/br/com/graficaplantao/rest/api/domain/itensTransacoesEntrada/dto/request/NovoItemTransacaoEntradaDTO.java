package br.com.graficaplantao.rest.api.domain.itensTransacoesEntrada.dto.request;

import br.com.graficaplantao.rest.api.domain.categorias.Unidade;
import jakarta.validation.Valid;
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
        public NovoItemTransacaoEntradaDTO(@Valid AtualizacaoItemTransacaoEntradaDTO itemAtualizado) {
                this(itemAtualizado.idMaterial(),itemAtualizado.undCom(),itemAtualizado.quantCom(),itemAtualizado.valorUntCom(), itemAtualizado.valorIpi(), itemAtualizado.obs());
        }
}
