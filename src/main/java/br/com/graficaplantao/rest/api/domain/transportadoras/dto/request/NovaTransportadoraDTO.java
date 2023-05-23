package br.com.graficaplantao.rest.api.domain.transportadoras.dto.request;

import jakarta.validation.constraints.NotBlank;

public record NovaTransportadoraDTO(

        @NotBlank
        String cnpj,

        @NotBlank
        String razaoSocial,

        String nomeFantasia,

        String fone
) {
}
