package br.com.graficaplantao.rest.api.domain.transacoesEntrada.dto.response;

import br.com.graficaplantao.rest.api.domain.transacoesEntrada.TransacaoEntrada;
import br.com.graficaplantao.rest.api.domain.transacoesEntrada.itensTransacoesEntrada.ItemTransacaoEntrada;
import br.com.graficaplantao.rest.api.domain.transacoesEntrada.itensTransacoesEntrada.dto.request.NovoItemTransacaoEntradaDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record ListagemTransacaoEntradaDTO(

        Long id,

        String nfe,

        LocalDateTime data_emissao,

        LocalDateTime data_recebimento,

        BigDecimal valor_total,

        BigDecimal valor_frete,

        BigDecimal valor_ipi_total,

        String obs,

        Long transportadora_id,

        Long fornecedora_id,

        List<NovoItemTransacaoEntradaDTO> itens
) {
    public ListagemTransacaoEntradaDTO(TransacaoEntrada transacaoEntrada) {
        this(
                transacaoEntrada.getId(),
                transacaoEntrada.getNfe(),
                transacaoEntrada.getData_emissao(),
                transacaoEntrada.getData_recebimento(),
                transacaoEntrada.getValor_total(),
                transacaoEntrada.getValor_frete(),
                transacaoEntrada.getValor_ipi_total(),
                transacaoEntrada.getObs(),
                transacaoEntrada.getTransportadora().getId(),
                transacaoEntrada.getFornecedora().getId(),
                toDTO(transacaoEntrada.getItens())
        );
    }

    private static List<NovoItemTransacaoEntradaDTO> toDTO(List<ItemTransacaoEntrada> itens) {
        return itens.stream().map(item -> new NovoItemTransacaoEntradaDTO(
                item.getMaterial().getId(),
                item.getUnd_com(),
                item.getQuant_com(),
                item.getValor_unt_com(),
                item.getValor_ipi(),
                item.getObs()
        )).collect(Collectors.toList());
    }
}
