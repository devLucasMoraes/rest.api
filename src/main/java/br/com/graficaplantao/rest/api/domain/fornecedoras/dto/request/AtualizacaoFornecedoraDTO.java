package br.com.graficaplantao.rest.api.domain.fornecedoras.dto.request;

import jakarta.validation.constraints.NotNull;

public record AtualizacaoFornecedoraDTO(

        @NotNull
        Long id,

        String cnpj,

        String razao_social,

        String nome_fantasia,

        String fone
) {
}
