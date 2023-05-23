package br.com.graficaplantao.rest.api.domain.fornecedoras.dto.response;

import br.com.graficaplantao.rest.api.domain.fornecedoras.Fornecedora;

public record DetalhamentoFornecedoraDTO(

        Long id,

        String cnpj,

        String razaoSocial,

        String nomeFantasia,

        String fone
) {
    public DetalhamentoFornecedoraDTO(Fornecedora fornecedora) {
        this(fornecedora.getId(), fornecedora.getCnpj(), fornecedora.getRazaoSocial(), fornecedora.getNomeFantasia(), fornecedora.getFone());
    }
}
