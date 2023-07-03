package br.com.graficaplantao.rest.api.domain.materiais.dto.response;

import br.com.graficaplantao.rest.api.domain.materiais.Material;

import java.math.BigDecimal;

public record ListagemMateriaisDTO(

        Long id,

        String descricao,

        BigDecimal valorUnt,

        BigDecimal qtdEmEstoque,

        Long idCategoria
) {
    public ListagemMateriaisDTO(Material material) {
        this(material.getId(), material.getDescricao(), material.getValorUnt(), material.getQtdEmEstoque(), material.getCategoria().getId());
    }
}
