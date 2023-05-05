package br.com.graficaplantao.rest.api.domain.transacoesEntrada.itensTransacoesEntrada;

import br.com.graficaplantao.rest.api.domain.materiais.Material;
import br.com.graficaplantao.rest.api.domain.transacoesEntrada.TransacaoEntrada;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "itens_transacoes_entrada")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class ItemTransacaoEntrada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "materiais_id")
    private Material material;

    @ManyToOne
    @JoinColumn(name = "transacoes_entrada_id")
    private TransacaoEntrada transacaoEntrada;

    private String und_com;

    private BigDecimal quant_com;

    private BigDecimal valor_unt_com;

    private BigDecimal valor_ipi;

    private String obs;

    public void update(AtualizacaoItemTransacaoEntradaDTO itemAtualizado, Material material) {
        if(itemAtualizado.materiais_id() != null) {
            this.material = material;
        }
        if(itemAtualizado.und_com() != null) {
            this.und_com = itemAtualizado.und_com();
        }
        if(itemAtualizado.quant_com() != null) {
            this.quant_com = itemAtualizado.quant_com();
        }
        if(itemAtualizado.valor_unt_com() != null) {
            this.valor_unt_com = itemAtualizado.valor_unt_com();
        }
        if(itemAtualizado.valor_ipi() != null) {
            this.valor_ipi = itemAtualizado.valor_ipi();
        }
        if(itemAtualizado.obs() != null) {
            this.obs = itemAtualizado.obs();
        }
    }
}
