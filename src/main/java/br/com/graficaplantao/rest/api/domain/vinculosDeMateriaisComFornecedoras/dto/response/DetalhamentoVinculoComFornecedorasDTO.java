package br.com.graficaplantao.rest.api.domain.vinculosDeMateriaisComFornecedoras.dto.response;

import br.com.graficaplantao.rest.api.domain.conversoesDeCompra.dto.response.DetalhamentoConversaoDeCompraDTO;

import java.util.List;

public record DetalhamentoVinculoComFornecedorasDTO(

        Long id,

        Long idFornecedora,

        Long idMaterial,

        String codProd,

        List<DetalhamentoConversaoDeCompraDTO> conversoesDeCompra
) {
}
