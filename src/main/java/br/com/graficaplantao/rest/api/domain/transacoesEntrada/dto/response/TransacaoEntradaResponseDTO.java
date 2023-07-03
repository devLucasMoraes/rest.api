package br.com.graficaplantao.rest.api.domain.transacoesEntrada.dto.response;

import br.com.graficaplantao.rest.api.domain.transacoesEntrada.TransacaoEntrada;
import br.com.graficaplantao.rest.api.domain.transacoesEntrada.itensTransacoesEntrada.dto.request.AtualizacaoItemTransacaoEntradaDTO;
import br.com.graficaplantao.rest.api.domain.transacoesEntrada.itensTransacoesEntrada.ItemTransacaoEntrada;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record TransacaoEntradaResponseDTO(

        Long id,

        String nfe,

        LocalDateTime dataEmissao,

        LocalDateTime dataRecebimento,

        BigDecimal valorTotal,

        BigDecimal valorFrete,

        BigDecimal valorIpiTotal,

        String obs,

        Long idTransportadora,

        Long idFornecedora,

        List<AtualizacaoItemTransacaoEntradaDTO> itens
) {
    public TransacaoEntradaResponseDTO(TransacaoEntrada transacaoEntrada) {
        this(
                transacaoEntrada.getId(),
                transacaoEntrada.getNfe(),
                transacaoEntrada.getDataEmissao(),
                transacaoEntrada.getDataRecebimento(),
                transacaoEntrada.getValorTotal(),
                transacaoEntrada.getValorFrete(),
                transacaoEntrada.getValorIpiTotal(),
                transacaoEntrada.getObs(),
                transacaoEntrada.getTransportadora().getId(),
                transacaoEntrada.getFornecedora().getId(),
                toDTO(transacaoEntrada.getItens())
        );
    }

    private static List<AtualizacaoItemTransacaoEntradaDTO> toDTO(List<ItemTransacaoEntrada> itens) {
        return itens.stream().map(item -> new AtualizacaoItemTransacaoEntradaDTO(
                item.getMaterial().getId(),
                item.getUndCom(),
                item.getQuantCom(),
                item.getValorUntCom(),
                item.getValorIpi(),
                item.getObs()
        )).collect(Collectors.toList());
    }

}
