package br.com.graficaplantao.rest.api.domain.categorias.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record AtualizacaoCategoriaDTO(

        @NotNull
        Long id,

        @NotBlank
        String nome,

        @NotBlank
        String und_padrao,

        @NotNull
        BigDecimal estoque_minimo

) {
}
