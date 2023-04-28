package br.com.graficaplantao.rest.api.domain.materiais;

import br.com.graficaplantao.rest.api.domain.categorias.Categoria;
import br.com.graficaplantao.rest.api.domain.materiais.dto.request.AtualizacaoMaterialDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "materiais")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cod_prod;

    private String descricao;

    private BigDecimal valor_unt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categorias_id")
    private Categoria categoria;

    public void update(AtualizacaoMaterialDTO dados, Categoria categoria) {
        if(dados.cod_prod() != null) {
            this.cod_prod = dados.cod_prod();
        }
        if(dados.descricao() != null) {
            this.descricao = dados.descricao();
        }
        if(dados.valor_unt() != null) {
            this.valor_unt = dados.valor_unt();
        }
        if(dados.categorias_id() != null) {
            this.categoria = categoria;
        }
    }
}
