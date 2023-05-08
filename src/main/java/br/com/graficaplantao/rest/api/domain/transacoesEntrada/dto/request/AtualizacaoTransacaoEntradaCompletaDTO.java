package br.com.graficaplantao.rest.api.domain.transacoesEntrada.dto.request;

import br.com.graficaplantao.rest.api.domain.transacoesEntrada.itensTransacoesEntrada.dto.request.AtualizacaoItemTransacaoEntradaDTO;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record AtualizacaoTransacaoEntradaCompletaDTO(

        Long id,

        String nfe,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "EEE, dd MMM yyyy HH:mm:ss z", locale = "pt_BR")
        LocalDateTime data_emissao,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "EEE, dd MMM yyyy HH:mm:ss z", locale = "pt_BR")
        LocalDateTime data_recebimento,

        BigDecimal valor_frete,

        String obs,


        Long transportadora_id,


        Long fornecedora_id,


        List<AtualizacaoItemTransacaoEntradaDTO> itens
) {
}
