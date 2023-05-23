package br.com.graficaplantao.rest.api.domain.materiais.vinculosComFornecedoras.dto.response;

public record DetalhamentoVinculoComFornecedorasDTO(

        Long id,

        Long idFornecedora,

        Long idMaterial
) {
}
