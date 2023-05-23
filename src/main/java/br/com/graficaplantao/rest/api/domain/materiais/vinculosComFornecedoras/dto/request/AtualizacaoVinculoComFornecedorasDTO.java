package br.com.graficaplantao.rest.api.domain.materiais.vinculosComFornecedoras.dto.request;

import jakarta.validation.constraints.NotNull;

public record AtualizacaoVinculoComFornecedorasDTO(

        @NotNull
        Long id,

        Long idFornecedora,

        String codProd
) {
}
