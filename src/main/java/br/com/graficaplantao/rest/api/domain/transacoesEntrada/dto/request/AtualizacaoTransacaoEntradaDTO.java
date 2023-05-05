package br.com.graficaplantao.rest.api.domain.transacoesEntrada.dto.request;

import br.com.graficaplantao.rest.api.domain.transacoesEntrada.itensTransacoesEntrada.AtualizacaoItemTransacaoEntradaDTO;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

public record AtualizacaoTransacaoEntradaDTO(

        @NotNull
        Long id,

        String nfe,

        LocalDateTime data_emissao,

        LocalDateTime data_recebimento,

        BigDecimal valor_frete,

        String obs,


        Long transportadora_id,


        Long fornecedora_id,


        ArrayList<AtualizacaoItemTransacaoEntradaDTO> itens
) {
}
