package br.com.graficaplantao.rest.api.domain.transacoesEntrada.dto.request;

import br.com.graficaplantao.rest.api.domain.transacoesEntrada.itensTransacoesEntrada.NovoItemTransacaoEntradaDTO;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

public record NovaTransacaoEntradaDTO(

        String nfe,

        LocalDateTime data_emissao,
        @NotNull
        LocalDateTime data_recebimento,

        BigDecimal valor_frete,

        String obs,

        @NotNull
        Long transportadora_id,

        @NotNull
        Long fornecedora_id,

        @NotNull
        ArrayList<NovoItemTransacaoEntradaDTO> itens
) {
}
