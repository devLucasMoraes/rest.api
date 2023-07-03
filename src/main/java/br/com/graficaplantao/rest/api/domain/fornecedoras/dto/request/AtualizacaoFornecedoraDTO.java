package br.com.graficaplantao.rest.api.domain.fornecedoras.dto.request;

import jakarta.validation.constraints.NotNull;

public record AtualizacaoFornecedoraDTO(

        Long id,

        String cnpj,

        String razaoSocial,

        String nomeFantasia,

        String fone
) {
}
