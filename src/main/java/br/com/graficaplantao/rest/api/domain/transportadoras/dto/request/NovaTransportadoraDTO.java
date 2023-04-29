package br.com.graficaplantao.rest.api.domain.transportadoras.dto.request;

import jakarta.validation.constraints.NotBlank;

public record NovaTransportadoraDTO(

        @NotBlank
        String cnpj,

        @NotBlank
        String razao_social,

        String nome_fantasia,

        String fone
) {
}
