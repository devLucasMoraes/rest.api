package br.com.graficaplantao.rest.api.domain.materiais.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record NovoMaterialDTO(
        String cod_prod,

        @NotBlank
        String descricao,
        @NotNull
        BigDecimal valor_unt,

        @NotNull
        Long categorias_id
) {
}
