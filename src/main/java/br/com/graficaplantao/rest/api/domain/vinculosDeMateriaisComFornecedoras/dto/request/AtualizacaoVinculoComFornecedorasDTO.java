package br.com.graficaplantao.rest.api.domain.vinculosDeMateriaisComFornecedoras.dto.request;

import br.com.graficaplantao.rest.api.domain.conversoesDeCompra.dto.request.AtualizacaoConversaoDeCompraDTO;

import java.util.ArrayList;

public record AtualizacaoVinculoComFornecedorasDTO(


        Long id,

        Long idFornecedora,

        String codProd,

        ArrayList<AtualizacaoConversaoDeCompraDTO> conversoesDeCompra
) {
}
