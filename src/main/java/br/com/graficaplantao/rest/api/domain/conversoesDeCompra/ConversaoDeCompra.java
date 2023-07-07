package br.com.graficaplantao.rest.api.domain.conversoesDeCompra;

import br.com.graficaplantao.rest.api.domain.categorias.Unidade;
import br.com.graficaplantao.rest.api.domain.vinculosDeMateriaisComFornecedoras.VinculoMaterialComFornecedora;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "conversoes_de_compra")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class ConversaoDeCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @JoinColumn(name = "und_compra")
    private Unidade undCompra;

    @Enumerated(EnumType.STRING)
    @JoinColumn(name = "und_padrao")
    private Unidade undPadrao;

    @JoinColumn(name = "fator_de_conversao")
    private BigDecimal fatorDeConversao;

    @ManyToOne
    @JoinColumn(name = "vinculos_materiais_fornecedoras_id")
    private VinculoMaterialComFornecedora vinculoComFornecedoras;

}
