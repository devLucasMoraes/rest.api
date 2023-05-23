package br.com.graficaplantao.rest.api.domain.categorias.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record NovaCategoriaDTO(
        @NotBlank
        String nome,

        @NotBlank
        String undPadrao,

        @NotNull
        BigDecimal estoqueMinimo
) {
}
