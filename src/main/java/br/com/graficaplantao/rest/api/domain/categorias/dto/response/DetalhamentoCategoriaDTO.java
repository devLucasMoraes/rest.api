package br.com.graficaplantao.rest.api.domain.categorias.dto.response;

import br.com.graficaplantao.rest.api.domain.categorias.Categoria;

import java.math.BigDecimal;

public record DetalhamentoCategoriaDTO(
        Long id,

        String nome,

        String undPadrao,

        BigDecimal estoqueMinimo
) {
    public DetalhamentoCategoriaDTO(Categoria categoria) {
        this(categoria.getId(), categoria.getNome(), categoria.getUndPadrao(), categoria.getEstoqueMinimo());
    }
}
