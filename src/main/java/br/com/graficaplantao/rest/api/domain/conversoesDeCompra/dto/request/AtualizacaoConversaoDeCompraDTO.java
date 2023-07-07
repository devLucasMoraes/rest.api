package br.com.graficaplantao.rest.api.domain.conversoesDeCompra.dto.request;

import br.com.graficaplantao.rest.api.domain.categorias.Unidade;

import java.math.BigDecimal;

public record AtualizacaoConversaoDeCompraDTO(


        Unidade undCompra,


        Unidade undPadrao,


        BigDecimal fatorDeConversao
) {
}
