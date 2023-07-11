package br.com.graficaplantao.rest.api.domain.vinculosDeMateriaisComFornecedoras;

import br.com.graficaplantao.rest.api.domain.conversoesDeCompra.ConversaoDeCompra;
import br.com.graficaplantao.rest.api.domain.fornecedoras.Fornecedora;
import br.com.graficaplantao.rest.api.domain.materiais.Material;
import br.com.graficaplantao.rest.api.domain.vinculosDeMateriaisComFornecedoras.dto.request.AtualizacaoVinculoComFornecedorasDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "vinculos_materiais_fornecedoras")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class VinculoMaterialComFornecedora {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "cod_prod")
    private String codProd;

    @ManyToOne
    @JoinColumn(name = "materiais_id")
    private Material material;

    @ManyToOne
    @JoinColumn(name = "fornecedoras_id")
    private Fornecedora fornecedora;

    @OneToMany(mappedBy = "vinculoComFornecedoras", cascade = CascadeType.ALL)
    private List<ConversaoDeCompra> conversaoDeCompras = new ArrayList<>();

    public void adicionarConversao(ConversaoDeCompra conversao) {
        this.conversaoDeCompras.add(conversao);
    }
    public void removerConversao(ConversaoDeCompra conversao) {
        this.conversaoDeCompras.remove(conversao);
    }

    public void update(AtualizacaoVinculoComFornecedorasDTO vinculoAtualizado, Fornecedora fornecedora, Material material) {
        this.material = material;

        if (vinculoAtualizado.codProd() != null) {
            this.codProd = vinculoAtualizado.codProd();
        }
        if (vinculoAtualizado.idFornecedora() != null) {
            this.fornecedora = fornecedora;
        }
    }
}
