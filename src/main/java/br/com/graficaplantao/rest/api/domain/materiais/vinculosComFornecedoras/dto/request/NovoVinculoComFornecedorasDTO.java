package br.com.graficaplantao.rest.api.domain.materiais.vinculosComFornecedoras.dto.request;

import br.com.graficaplantao.rest.api.domain.materiais.vinculosComFornecedoras.conversoesDeCompra.dto.request.NovaConversaoDeCompraDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;

public record NovoVinculoComFornecedorasDTO(

        @NotNull
        Long idFornecedora,

        @NotBlank
        String codProd,

        ArrayList<NovaConversaoDeCompraDTO> conversoesDeCompra
) {
}
