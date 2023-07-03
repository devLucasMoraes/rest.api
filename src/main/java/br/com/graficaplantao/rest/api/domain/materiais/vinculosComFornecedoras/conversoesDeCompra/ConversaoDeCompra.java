package br.com.graficaplantao.rest.api.domain.materiais.vinculosComFornecedoras.conversoesDeCompra;

import br.com.graficaplantao.rest.api.domain.materiais.vinculosComFornecedoras.VinculoComFornecedoras;
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

    @JoinColumn(name = "und_compra")
    private String undCompra;

    @JoinColumn(name = "und_padrao")
    private String undPadrao;

    @JoinColumn(name = "fator_de_conversao")
    private BigDecimal fatorDeConversao;

    @ManyToOne
    @JoinColumn(name = "vinculos_materiais_fornecedoras_id")
    private VinculoComFornecedoras vinculoComFornecedoras;
}
