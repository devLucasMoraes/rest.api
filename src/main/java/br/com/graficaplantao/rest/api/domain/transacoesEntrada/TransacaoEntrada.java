package br.com.graficaplantao.rest.api.domain.transacoesEntrada;

import br.com.graficaplantao.rest.api.domain.fornecedoras.Fornecedora;
import br.com.graficaplantao.rest.api.domain.transacoesEntrada.dto.request.AtualizacaoTransacaoEntradaDTO;
import br.com.graficaplantao.rest.api.domain.transacoesEntrada.itensTransacoesEntrada.ItemTransacaoEntrada;
import br.com.graficaplantao.rest.api.domain.transportadoras.Transportadora;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "transacoes_entrada")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class TransacaoEntrada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nfe;

    private LocalDateTime data_emissao;

    private LocalDateTime data_recebimento;

    private BigDecimal valor_total;

    private BigDecimal valor_frete;

    private BigDecimal valor_ipi_total;

    private String obs;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transportadoras_id")
    private Transportadora transportadora;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fornecedoras_id")
    private Fornecedora fornecedora;

    @OneToMany(mappedBy = "transacaoEntrada", cascade = CascadeType.ALL)
    private List<ItemTransacaoEntrada> itens = new ArrayList<>();

    public void adicionarItem(ItemTransacaoEntrada item) {
        this.itens.add(item);
    }
    public void removerItem(ItemTransacaoEntrada item) {
        this.itens.remove(item);
    }

    public void update(AtualizacaoTransacaoEntradaDTO dados, Transportadora transportadora, Fornecedora fornecedora, ArrayList<ItemTransacaoEntrada> itensParaAtualizar) {
        if(dados.nfe() != null) {
            this.nfe = dados.nfe();
        }
        if(dados.data_emissao() != null) {
            this.data_emissao = dados.data_emissao();
        }
        if(dados.data_recebimento() != null) {
            this.data_recebimento = dados.data_recebimento();
        }
        if(dados.valor_frete() != null) {
            this.valor_frete = dados.valor_frete();
        }
        if(dados.obs() != null) {
            this.obs = dados.obs();
        }
        if(dados.transportadora_id() != null) {
            this.transportadora = transportadora;
        }
        if(dados.fornecedora_id() != null) {
            this.fornecedora = fornecedora;
        }
        if(dados.itens() != null) {
            this.itens = itensParaAtualizar;
        }
    }
}
