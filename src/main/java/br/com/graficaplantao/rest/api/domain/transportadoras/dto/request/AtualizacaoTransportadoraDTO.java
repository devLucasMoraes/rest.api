package br.com.graficaplantao.rest.api.domain.transportadoras.dto.request;

import jakarta.validation.constraints.NotNull;

public record AtualizacaoTransportadoraDTO(

        @NotNull
        Long id,

        String cnpj,

        String razaoSocial,

        String nomeFantasia,

        String fone
) {
}
