package br.com.graficaplantao.rest.api.domain.transacoesEntrada;

import br.com.graficaplantao.rest.api.domain.fornecedoras.Fornecedora;
import br.com.graficaplantao.rest.api.domain.transacoesEntrada.dto.request.AtualizacaoTransacaoEntradaCompletaDTO;
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

    @JoinColumn(name = "nfe")
    private String nfe;

    @JoinColumn(name = "data_emissao")
    private LocalDateTime dataEmissao;

    @JoinColumn(name = "data_recebimento")
    private LocalDateTime dataRecebimento;

    @JoinColumn(name = "valor_total")
    private BigDecimal valorTotal;

    @JoinColumn(name = "valor_frete")
    private BigDecimal valorFrete;

    @JoinColumn(name = "valor_ipi_total")
    private BigDecimal valorIpiTotal;

    @JoinColumn(name = "obs")
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

    public void update(AtualizacaoTransacaoEntradaCompletaDTO dados, Transportadora transportadora, Fornecedora fornecedora, List<ItemTransacaoEntrada> itensParaAtualizar) {
        if(dados.nfe() != null) {
            this.nfe = dados.nfe();
        }
        if(dados.dataEmissao() != null) {
            this.dataEmissao = dados.dataEmissao();
        }
        if(dados.dataRecebimento() != null) {
            this.dataRecebimento = dados.dataRecebimento();
        }
        if(dados.valorFrete() != null) {
            this.valorFrete = dados.valorFrete();
        }
        if(dados.obs() != null) {
            this.obs = dados.obs();
        }
        if(dados.idTransportadora() != null) {
            this.transportadora = transportadora;
        }
        if(dados.idFornecedora() != null) {
            this.fornecedora = fornecedora;
        }
        if(dados.itens() != null) {
            this.itens = itensParaAtualizar;
        }
    }
}
