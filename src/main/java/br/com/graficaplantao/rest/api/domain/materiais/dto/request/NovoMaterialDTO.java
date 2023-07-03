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
        BigDecimal valorUnt,
        @NotNull
        Long idCategoria,

        ArrayList<NovoVinculoComFornecedorasDTO> fornecedorasVinculadas
) {
}
