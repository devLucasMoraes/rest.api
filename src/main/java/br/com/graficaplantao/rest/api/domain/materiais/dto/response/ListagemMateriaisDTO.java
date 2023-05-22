package br.com.graficaplantao.rest.api.domain.materiais.dto.response;

import br.com.graficaplantao.rest.api.domain.materiais.Material;

import java.math.BigDecimal;

public record ListagemMateriaisDTO(

        Long id,

        String descricao,

        BigDecimal valor_unt,

        Long categorias_id
) {
    public ListagemMateriaisDTO(Material material) {
        this(material.getId(), material.getDescricao(), material.getValor_unt(), material.getCategoria().getId());
    }
}
