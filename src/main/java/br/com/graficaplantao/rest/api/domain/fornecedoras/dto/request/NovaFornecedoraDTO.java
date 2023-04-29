package br.com.graficaplantao.rest.api.domain.fornecedoras.dto.request;

import jakarta.validation.constraints.NotBlank;

public record NovaFornecedoraDTO(

        @NotBlank
        String cnpj,

        @NotBlank
        String razao_social,

        String nome_fantasia,

        String fone
) {
}
