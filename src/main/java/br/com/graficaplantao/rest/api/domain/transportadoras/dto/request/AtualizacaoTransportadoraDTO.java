package br.com.graficaplantao.rest.api.domain.transportadoras.dto.request;

public record AtualizacaoTransportadoraDTO(

        Long id,

        String cnpj,

        String razaoSocial,

        String nomeFantasia,

        String fone
) {
}
