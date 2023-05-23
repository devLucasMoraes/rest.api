package br.com.graficaplantao.rest.api.domain.fornecedoras.dto.request;

import jakarta.validation.constraints.NotBlank;

public record NovaFornecedoraDTO(

        @NotBlank
        String cnpj,

        @NotBlank
        String razaoSocial,

        String nomeFantasia,

        String fone
) {
}
