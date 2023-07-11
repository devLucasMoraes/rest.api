package br.com.graficaplantao.rest.api.domain.vinculosDeMateriaisComFornecedoras.dto.request;

import br.com.graficaplantao.rest.api.domain.conversoesDeCompra.dto.request.AtualizacaoConversaoDeCompraDTO;
import br.com.graficaplantao.rest.api.domain.conversoesDeCompra.dto.request.NovaConversaoDeCompraDTO;
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
    public NovoVinculoComFornecedorasDTO(AtualizacaoVinculoComFornecedorasDTO vinculoAtualizado) {
        this(vinculoAtualizado.idFornecedora(), vinculoAtualizado.codProd(), new ArrayList<>());
    }
}
