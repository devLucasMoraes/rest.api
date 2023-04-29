package br.com.graficaplantao.rest.api.domain.transportadoras.dto.response;

import br.com.graficaplantao.rest.api.domain.transportadoras.Transportadora;

public record DetalhamentoTransportadoraDTO(

        Long id,

        String cnpj,

        String razao_social,

        String nome_fantasia,

        String fone
) {
    public DetalhamentoTransportadoraDTO(Transportadora transportadora) {
        this(transportadora.getId(), transportadora.getCnpj(), transportadora.getRazao_social(), transportadora.getNome_fantasia(), transportadora.getFone());
    }
}
