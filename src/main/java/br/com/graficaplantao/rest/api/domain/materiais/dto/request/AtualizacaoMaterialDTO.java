package br.com.graficaplantao.rest.api.domain.materiais.dto.request;

import br.com.graficaplantao.rest.api.domain.materiais.vinculosComFornecedoras.dto.request.AtualizacaoVinculoComFornecedorasDTO;

import java.math.BigDecimal;
import java.util.ArrayList;

public record AtualizacaoMaterialDTO(


        Long id,

        String descricao,

        BigDecimal valorUnt,

        Long idCategoria,

        ArrayList<AtualizacaoVinculoComFornecedorasDTO> fornecedorasVinculadas
) {
}
