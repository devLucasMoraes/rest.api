package br.com.graficaplantao.rest.api.domain.conversoesDeCompra.dto.request;

import br.com.graficaplantao.rest.api.domain.categorias.Unidade;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record NovaConversaoDeCompraDTO(

        @NotNull
        Unidade undCompra,

        @NotNull
        Unidade undPadrao,

        @NotNull
        BigDecimal fatorDeConversao
) {
}
