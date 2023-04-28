package br.com.graficaplantao.rest.api.domain.materiais.dto.request;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record AtualizacaoMaterialDTO(

        @NotNull
        Long id,

        String cod_prod,

        String descricao,

        BigDecimal valor_unt,

        Long categorias_id
) {
}
