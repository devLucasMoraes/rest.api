package br.com.graficaplantao.rest.api.domain.materiais.dto.response;

import br.com.graficaplantao.rest.api.domain.materiais.Material;

import java.math.BigDecimal;

public record DetalhamentoMaterialDTO(
        Long id,

        String cod_prod,

        String descricao,

        BigDecimal valor_unt,

        Long categoria_id
) {
    public DetalhamentoMaterialDTO(Material material) {
        this(material.getId(), material.getCod_prod(), material.getDescricao(), material.getValor_unt(), material.getCategoria().getId());
    }
}
