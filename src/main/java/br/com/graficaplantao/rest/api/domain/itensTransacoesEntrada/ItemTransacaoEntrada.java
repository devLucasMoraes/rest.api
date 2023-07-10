package br.com.graficaplantao.rest.api.domain.itensTransacoesEntrada;

import br.com.graficaplantao.rest.api.domain.categorias.Unidade;
import br.com.graficaplantao.rest.api.domain.itensTransacoesEntrada.dto.request.AtualizacaoItemTransacaoEntradaDTO;
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

    @Enumerated(EnumType.STRING)
    @JoinColumn(name = "und_com")
    private Unidade undCom;

    @JoinColumn(name = "quant_com")
    private BigDecimal quantCom;

    @JoinColumn(name = "valor_unt_com")
    private BigDecimal valorUntCom;

    @JoinColumn(name = "valor_ipi")
    private BigDecimal valorIpi;

    private String obs;

    public void update(AtualizacaoItemTransacaoEntradaDTO itemAtualizado, Material material, TransacaoEntrada transacaoEntrada) {
        this.material = material;
        this.transacaoEntrada = transacaoEntrada;
        if(itemAtualizado.undCom() != null) {
            this.undCom = itemAtualizado.undCom();
        }
        if(itemAtualizado.quantCom() != null) {
            this.quantCom = itemAtualizado.quantCom();
        }
        if(itemAtualizado.valorUntCom() != null) {
            this.valorUntCom = itemAtualizado.valorUntCom();
        }
        if(itemAtualizado.valorIpi() != null) {
            this.valorIpi = itemAtualizado.valorIpi();
        }
        if(itemAtualizado.obs() != null) {
            this.obs = itemAtualizado.obs();
        }
    }
}
