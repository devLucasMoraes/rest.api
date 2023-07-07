package br.com.graficaplantao.rest.api.domain.materiais.dto.response;

import br.com.graficaplantao.rest.api.domain.conversoesDeCompra.dto.response.DetalhamentoConversaoDeCompraDTO;
import br.com.graficaplantao.rest.api.domain.materiais.Material;
import br.com.graficaplantao.rest.api.domain.vinculosDeMateriaisComFornecedoras.VinculoMaterialComFornecedora;
import br.com.graficaplantao.rest.api.domain.vinculosDeMateriaisComFornecedoras.dto.response.DetalhamentoVinculoComFornecedorasDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public record DetalhamentoMaterialDTO(
        Long id,

        String descricao,

        BigDecimal valorUnt,

        BigDecimal qtdEmEstoque,

        Long idCategoria,

        List<DetalhamentoVinculoComFornecedorasDTO> fornecedorasVinculadas
) {
    public DetalhamentoMaterialDTO(Material material) {
        this(material.getId(), material.getDescricao(), material.getValorUnt(), material.getQtdEmEstoque(), material.getCategoria().getId(), toDTO(material.getFornecedorasVinculadas()));
    }

    private static List<DetalhamentoVinculoComFornecedorasDTO> toDTO(List<VinculoMaterialComFornecedora> vinculo) {
        return vinculo.stream().map(item -> new DetalhamentoVinculoComFornecedorasDTO(
                item.getId(),
                item.getFornecedora().getId(),
                item.getMaterial().getId(),
                item.getCodProd(),
                item.getConversaoDeCompras().stream().map(conversaoDeCompra -> new DetalhamentoConversaoDeCompraDTO(conversaoDeCompra))
                        .collect(Collectors.toList())
        )).collect(Collectors.toList());
    }
}
