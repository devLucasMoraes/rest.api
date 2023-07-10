package br.com.graficaplantao.rest.api.domain.transacoesEntrada.dto.request;

import br.com.graficaplantao.rest.api.domain.itensTransacoesEntrada.dto.request.NovoItemTransacaoEntradaDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

public record NovaTransacaoEntradaDTO(

        String nfe,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", locale = "pt_BR")
        LocalDateTime dataEmissao,
        @NotNull
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", locale = "pt_BR")
        LocalDateTime dataRecebimento,

        BigDecimal valorFrete,

        String obs,

        @NotNull
        Long idTransportadora,

        @NotNull
        Long idFornecedora,

        @Valid
        @NotNull
        ArrayList<NovoItemTransacaoEntradaDTO> itens
) {
}
