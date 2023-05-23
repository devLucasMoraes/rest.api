package br.com.graficaplantao.rest.api.domain.materiais;

import br.com.graficaplantao.rest.api.domain.categorias.Categoria;
import br.com.graficaplantao.rest.api.domain.materiais.dto.request.AtualizacaoMaterialDTO;
import br.com.graficaplantao.rest.api.domain.materiais.vinculosComFornecedoras.VinculoComFornecedoras;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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

    @JoinColumn(name = "descricao")
    private String descricao;

    @JoinColumn(name = "valor_unt")
    private BigDecimal valorUnt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categorias_id")
    private Categoria categoria;

    @OneToMany(mappedBy = "material", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VinculoComFornecedoras> fornecedorasVinculadas = new ArrayList<>();

    public void adicionarVinculo(VinculoComFornecedoras vinculo) {
        this.fornecedorasVinculadas.add(vinculo);
    }

    public void update(AtualizacaoMaterialDTO dados, Categoria categoria, List<VinculoComFornecedoras> vinculosParaAtualizar) {
        if(dados.descricao() != null) {
            this.descricao = dados.descricao();
        }
        if(dados.valorUnt() != null) {
            this.valorUnt = dados.valorUnt();
        }
        if(dados.idCategoria() != null) {
            this.categoria = categoria;
        }
        if(dados.fornecedorasVinculadas() != null) {
            this.fornecedorasVinculadas.clear();
            this.fornecedorasVinculadas.addAll(vinculosParaAtualizar);
        }
    }

}
