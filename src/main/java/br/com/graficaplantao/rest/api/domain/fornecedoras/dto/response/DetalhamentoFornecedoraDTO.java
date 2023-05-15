package br.com.graficaplantao.rest.api.domain.fornecedoras.dto.response;

import br.com.graficaplantao.rest.api.domain.fornecedoras.Fornecedora;

public record DetalhamentoFornecedoraDTO(

        Long id,

        String cnpj,

        String razao_social,

        String nome_fantasia,

        String fone
) {
    public DetalhamentoFornecedoraDTO(Fornecedora fornecedora) {
        this(fornecedora.getId(), fornecedora.getCnpj(), fornecedora.getRazao_social(), fornecedora.getNomeFantasia(), fornecedora.getFone());
    }
}
