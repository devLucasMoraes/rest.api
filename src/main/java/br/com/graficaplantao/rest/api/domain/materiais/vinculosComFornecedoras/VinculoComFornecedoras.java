package br.com.graficaplantao.rest.api.domain.materiais.vinculosComFornecedoras;

import br.com.graficaplantao.rest.api.domain.fornecedoras.Fornecedora;
import br.com.graficaplantao.rest.api.domain.materiais.Material;
import br.com.graficaplantao.rest.api.domain.materiais.vinculosComFornecedoras.dto.request.AtualizacaoVinculoComFornecedorasDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "vinculos_materiais_fornecedoras")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class VinculoComFornecedoras {

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

    public void update(AtualizacaoVinculoComFornecedorasDTO vinculoAtualizado, Fornecedora fornecedora) {
        if (vinculoAtualizado.codProd() != null) {
            this.codProd = vinculoAtualizado.codProd();
        }
        if (vinculoAtualizado.idFornecedora() != null) {
            this.fornecedora = fornecedora;
        }
    }
}
