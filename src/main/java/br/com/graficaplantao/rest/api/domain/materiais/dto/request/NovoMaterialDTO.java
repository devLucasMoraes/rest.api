package br.com.graficaplantao.rest.api.domain.materiais.dto.request;

import br.com.graficaplantao.rest.api.domain.materiais.vinculosComFornecedoras.dto.request.NovoVinculoComFornecedorasDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.ArrayList;

public record NovoMaterialDTO(
        @NotBlank
        String descricao,
        @NotNull
        BigDecimal valor_unt,

        @NotNull
        Long categorias_id,

        ArrayList<NovoVinculoComFornecedorasDTO> fornecedorasVinculadas
) {
}
