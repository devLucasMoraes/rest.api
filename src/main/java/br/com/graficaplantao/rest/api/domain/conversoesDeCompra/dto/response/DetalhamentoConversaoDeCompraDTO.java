package br.com.graficaplantao.rest.api.domain.conversoesDeCompra.dto.response;

import br.com.graficaplantao.rest.api.domain.categorias.Unidade;
import br.com.graficaplantao.rest.api.domain.conversoesDeCompra.ConversaoDeCompra;

import java.math.BigDecimal;

public record DetalhamentoConversaoDeCompraDTO(
        Unidade undCompra,

        Unidade undPadrao,

        BigDecimal fatorDeConversao
) {
    public DetalhamentoConversaoDeCompraDTO(ConversaoDeCompra conversaoDeCompras) {
        this(conversaoDeCompras.getUndCompra(), conversaoDeCompras.getUndPadrao(), conversaoDeCompras.getFatorDeConversao());
    }
}
