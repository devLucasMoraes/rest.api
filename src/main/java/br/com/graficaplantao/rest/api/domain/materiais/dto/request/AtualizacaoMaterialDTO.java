package br.com.graficaplantao.rest.api.domain.materiais.dto.request;

import br.com.graficaplantao.rest.api.domain.materiais.vinculosComFornecedoras.dto.request.AtualizacaoVinculoComFornecedorasDTO;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.ArrayList;

public record AtualizacaoMaterialDTO(

        @NotNull
        Long id,

        String descricao,

        BigDecimal valor_unt,

        Long categorias_id,

        ArrayList<AtualizacaoVinculoComFornecedorasDTO> fornecedorasVinculadas
) {
}
