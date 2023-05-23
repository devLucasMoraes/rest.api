package br.com.graficaplantao.rest.api.domain.materiais.dto.response;

import br.com.graficaplantao.rest.api.domain.materiais.Material;
import br.com.graficaplantao.rest.api.domain.materiais.vinculosComFornecedoras.VinculoComFornecedoras;
import br.com.graficaplantao.rest.api.domain.materiais.vinculosComFornecedoras.dto.request.AtualizacaoVinculoComFornecedorasDTO;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public record DetalhamentoMaterialDTO(
        Long id,

        String descricao,

        BigDecimal valorUnt,

        Long idCategoria,

        List<AtualizacaoVinculoComFornecedorasDTO> fornecedorasVinculadas
) {
    public DetalhamentoMaterialDTO(Material material) {
        this(material.getId(), material.getDescricao(), material.getValorUnt(), material.getCategoria().getId(), toDTO(material.getFornecedorasVinculadas()));
    }

    private static List<AtualizacaoVinculoComFornecedorasDTO> toDTO(List<VinculoComFornecedoras> vinculo) {
        return vinculo.stream().map(item -> new AtualizacaoVinculoComFornecedorasDTO(
                item.getId(),
                item.getFornecedora().getId(),
                item.getCodProd()
        )).collect(Collectors.toList());
    }
}
