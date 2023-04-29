package br.com.graficaplantao.rest.api.domain.fornecedoras.dto.response;

import br.com.graficaplantao.rest.api.domain.fornecedoras.Fornecedora;

public record ListagemFornecedoraDTO(

        Long id,

        String cnpj,

        String razao_social,

        String nome_fantasia,

        String fone
) {
    public ListagemFornecedoraDTO(Fornecedora fornecedora) {
        this(fornecedora.getId(), fornecedora.getCnpj(), fornecedora.getRazao_social(), fornecedora.getNome_fantasia(), fornecedora.getFone());
    }
}
