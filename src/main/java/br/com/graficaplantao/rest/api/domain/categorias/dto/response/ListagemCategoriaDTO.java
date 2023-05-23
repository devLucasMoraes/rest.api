package br.com.graficaplantao.rest.api.domain.categorias.dto.response;

import br.com.graficaplantao.rest.api.domain.categorias.Categoria;

import java.math.BigDecimal;

public record ListagemCategoriaDTO(
        Long id,

        String nome,

        String undPadrao,

        BigDecimal estoqueMinimo
) {
    public ListagemCategoriaDTO (Categoria categoria){
        this(categoria.getId(), categoria.getNome(), categoria.getUndPadrao(), categoria.getEstoqueMinimo());
    }
}
