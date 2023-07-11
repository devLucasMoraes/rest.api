package br.com.graficaplantao.rest.api.domain.conversoesDeCompra.services;

import br.com.graficaplantao.rest.api.domain.conversoesDeCompra.ConversaoDeCompra;
import br.com.graficaplantao.rest.api.domain.conversoesDeCompra.dto.request.AtualizacaoConversaoDeCompraDTO;
import br.com.graficaplantao.rest.api.domain.conversoesDeCompra.dto.request.NovaConversaoDeCompraDTO;
import br.com.graficaplantao.rest.api.domain.vinculosDeMateriaisComFornecedoras.VinculoMaterialComFornecedora;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConversaoDeCompraService {

    @Transactional
    public void atualizarConversoesDeCompra(List<AtualizacaoConversaoDeCompraDTO> listaAtualizadaDTO, VinculoMaterialComFornecedora vinculo) {


        for (var conversaoAtualizada : listaAtualizadaDTO) {

            ConversaoDeCompra conversaoDeCompra = vinculo.getConversaoDeCompras().stream()
                    .filter(item -> item.getUndCompra().equals(conversaoAtualizada.undCompra()))
                    .findFirst()
                    .orElseGet(() -> criarNovaConversao(new NovaConversaoDeCompraDTO(conversaoAtualizada), vinculo));

            conversaoDeCompra.update(conversaoAtualizada, vinculo);

        }

    }

    @Transactional
    public void adicionarConversoesDeCompra(ArrayList<NovaConversaoDeCompraDTO> ListaNovasConversaoesDeCompra, VinculoMaterialComFornecedora vinculo) {

        for (var novaConversao : ListaNovasConversaoesDeCompra) {

            criarNovaConversao(novaConversao, vinculo);

        }
    }

    private ConversaoDeCompra criarNovaConversao(NovaConversaoDeCompraDTO novaConversao, VinculoMaterialComFornecedora vinculo) {
        var conversao = new ConversaoDeCompra(
                null,
                novaConversao.undCompra(),
                novaConversao.undPadrao(),
                novaConversao.fatorDeConversao(),
                vinculo
        );

        vinculo.adicionarConversao(conversao);

        return conversao;
    }
}
