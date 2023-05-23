package br.com.graficaplantao.rest.api.domain.transportadoras.dto.response;

import br.com.graficaplantao.rest.api.domain.transportadoras.Transportadora;

public record DetalhamentoTransportadoraDTO(

        Long id,

        String cnpj,

        String razaoSocial,

        String nomeFantasia,

        String fone
) {
    public DetalhamentoTransportadoraDTO(Transportadora transportadora) {
        this(transportadora.getId(), transportadora.getCnpj(), transportadora.getRazaoSocial(), transportadora.getNomeFantasia(), transportadora.getFone());
    }
}
