package br.com.graficaplantao.rest.api.domain.itensTransacoesEntrada.dto.request;

import br.com.graficaplantao.rest.api.domain.categorias.Unidade;

import java.math.BigDecimal;

public record AtualizacaoItemTransacaoEntradaDTO(

        Long idMaterial,

        Unidade undCom,

        BigDecimal quantCom,

        BigDecimal valorUntCom,

        BigDecimal valorIpi,

        String obs
) {
}
