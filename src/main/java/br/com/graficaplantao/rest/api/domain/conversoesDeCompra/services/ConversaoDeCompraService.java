package br.com.graficaplantao.rest.api.domain.conversoesDeCompra.services;

import br.com.graficaplantao.rest.api.domain.conversoesDeCompra.ConversaoDeCompra;
import br.com.graficaplantao.rest.api.domain.conversoesDeCompra.dto.request.NovaConversaoDeCompraDTO;
import br.com.graficaplantao.rest.api.domain.vinculosDeMateriaisComFornecedoras.VinculoMaterialComFornecedora;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ConversaoDeCompraService {
    public void adicionarListaDeConversoesDeCompra(ArrayList<NovaConversaoDeCompraDTO> ListaNovasConversaoesDeCompra, VinculoMaterialComFornecedora vinculo) {

        for (var novaConversao : ListaNovasConversaoesDeCompra) {

            var conversao = new ConversaoDeCompra(
                    null,
                    novaConversao.undCompra(),
                    novaConversao.undPadrao(),
                    novaConversao.fatorDeConversao(),
                    vinculo
            );

            vinculo.adicionarConversao(conversao);

        }
    }
}
