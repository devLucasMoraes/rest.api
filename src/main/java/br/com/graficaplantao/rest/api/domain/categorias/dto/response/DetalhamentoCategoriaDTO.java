package br.com.graficaplantao.rest.api.domain.categorias.dto.response;

import br.com.graficaplantao.rest.api.domain.categorias.Categoria;

import java.math.BigDecimal;

public record DetalhamentoCategoriaDTO(
        Long id,

        String nome,

        String und_padrao,

        BigDecimal estoque_minimo
) {
    public DetalhamentoCategoriaDTO(Categoria categoria) {
        this(categoria.getId(), categoria.getNome(), categoria.getUnd_padrao(), categoria.getEstoque_minimo());
    }
}
