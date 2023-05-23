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

    @JoinColumn(name = "nome")
    private String nome;

    @JoinColumn(name = "und_padrao")
    private String undPadrao;

    @JoinColumn(name = "estoque_minimo")
    private BigDecimal estoqueMinimo;

    public Categoria(NovaCategoriaDTO dados) {
        this.nome = dados.nome();
        this.undPadrao = dados.undPadrao();
        this.estoqueMinimo = dados.estoqueMinimo();
    }

    public void update(AtualizacaoCategoriaDTO dados) {
        if(dados.nome() != null) {
            this.nome = dados.nome();
        }
        if(dados.undPadrao() != null) {
            this.undPadrao = dados.undPadrao();
        }
        if(dados.estoqueMinimo() != null) {
            this.estoqueMinimo = dados.estoqueMinimo();
        }
    }
}
