package br.com.graficaplantao.rest.api.domain.categorias.dto.request;

import br.com.graficaplantao.rest.api.domain.categorias.Unidade;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record NovaCategoriaDTO(
        @NotBlank
        String nome,

        @NotNull
        Unidade undPadrao,

        @NotNull
        BigDecimal estoqueMinimo
) {
}
