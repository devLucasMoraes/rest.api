package br.com.graficaplantao.rest.api.domain.categorias;

import br.com.graficaplantao.rest.api.domain.categorias.dto.request.AtualizacaoCategoriaDTO;
import br.com.graficaplantao.rest.api.domain.categorias.dto.request.NovaCategoriaDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "categorias")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String und_padrao;

    private BigDecimal estoque_minimo;

    public Categoria(NovaCategoriaDTO dados) {
        this.nome = dados.nome();
        this.und_padrao = dados.und_padrao();
        this.estoque_minimo = dados.estoque_minimo();
    }

    public void update(AtualizacaoCategoriaDTO dados) {
        if(dados.nome() != null) {
            this.nome = dados.nome();
        }
        if(dados.und_padrao() != null) {
            this.und_padrao = dados.und_padrao();
        }
        if(dados.estoque_minimo() != null) {
            this.estoque_minimo = dados.estoque_minimo();
        }
    }
}
