package br.com.graficaplantao.rest.api.domain.materiais.vinculosComFornecedoras.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record NovoVinculoComFornecedorasDTO(

        @NotNull
        Long fornecedora_id,

        @NotBlank
        String codProd
) {
}
