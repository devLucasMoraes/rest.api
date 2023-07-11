package br.com.graficaplantao.rest.api.domain.materiais;

import br.com.graficaplantao.rest.api.domain.categorias.Categoria;
import br.com.graficaplantao.rest.api.domain.materiais.dto.request.AtualizacaoMaterialDTO;
import br.com.graficaplantao.rest.api.domain.vinculosDeMateriaisComFornecedoras.VinculoMaterialComFornecedora;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "materiais")
@Getter
@Setter
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

    @JoinColumn(name = "qtd_em_estoque")
    private BigDecimal qtdEmEstoque;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categorias_id")
    private Categoria categoria;

    @OneToMany(mappedBy = "material", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VinculoMaterialComFornecedora> fornecedorasVinculadas = new ArrayList<>();

    public void adicionarVinculo(VinculoMaterialComFornecedora vinculo) {
        this.fornecedorasVinculadas.add(vinculo);
    }

    public void update(AtualizacaoMaterialDTO dados, Categoria categoria) {
        if(dados.descricao() != null) {
            this.descricao = dados.descricao();
        }
        if(dados.valorUnt() != null) {
            this.valorUnt = dados.valorUnt();
        }
        if(dados.idCategoria() != null) {
            this.categoria = categoria;
        }
    }

}
