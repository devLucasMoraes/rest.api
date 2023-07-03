package br.com.graficaplantao.rest.api.domain.materiais.vinculosComFornecedoras.conversoesDeCompra.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record NovaConversaoDeCompraDTO(

        @NotBlank
        String undCompra,

        @NotBlank
        String undPadrao,

        @NotNull
        BigDecimal fatorDeConversao
) {
}
