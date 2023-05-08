package br.com.graficaplantao.rest.api.domain.transacoesEntrada.dto.request;

import br.com.graficaplantao.rest.api.domain.transacoesEntrada.itensTransacoesEntrada.dto.request.NovoItemTransacaoEntradaDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

public record NovaTransacaoEntradaDTO(

        String nfe,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "EEE, dd MMM yyyy HH:mm:ss z", locale = "pt_BR")
        LocalDateTime data_emissao,
        @NotNull
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "EEE, dd MMM yyyy HH:mm:ss z", locale = "pt_BR")
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
