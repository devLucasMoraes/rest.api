package br.com.graficaplantao.rest.api.domain.itensTransacoesEntrada.dto.request;

import br.com.graficaplantao.rest.api.domain.categorias.Unidade;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record AtualizacaoItemTransacaoEntradaDTO(

        @NotNull
        Long idMaterial,

        Unidade undCom,

        @Positive(message = "Quantidade comprada deve ser maior que 0")
        BigDecimal quantCom,

        BigDecimal valorUntCom,

        BigDecimal valorIpi,

        String obs
) {
}
