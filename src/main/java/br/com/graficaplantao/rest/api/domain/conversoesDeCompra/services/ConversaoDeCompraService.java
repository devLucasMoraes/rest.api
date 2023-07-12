package br.com.graficaplantao.rest.api.domain.conversoesDeCompra.services;

import br.com.graficaplantao.rest.api.domain.categorias.Unidade;
import br.com.graficaplantao.rest.api.domain.conversoesDeCompra.ConversaoDeCompra;
import br.com.graficaplantao.rest.api.domain.conversoesDeCompra.ConversaoDeCompraRepository;
import br.com.graficaplantao.rest.api.domain.conversoesDeCompra.dto.request.AtualizacaoConversaoDeCompraDTO;
import br.com.graficaplantao.rest.api.domain.conversoesDeCompra.dto.request.NovaConversaoDeCompraDTO;
import br.com.graficaplantao.rest.api.domain.fornecedoras.Fornecedora;
import br.com.graficaplantao.rest.api.domain.itensTransacoesEntrada.ItemTransacaoEntrada;
import br.com.graficaplantao.rest.api.domain.itensTransacoesEntrada.dto.request.AtualizacaoItemTransacaoEntradaDTO;
import br.com.graficaplantao.rest.api.domain.materiais.Material;
import br.com.graficaplantao.rest.api.domain.materiais.services.MaterialService;
import br.com.graficaplantao.rest.api.domain.vinculosDeMateriaisComFornecedoras.VinculoMaterialComFornecedora;
import br.com.graficaplantao.rest.api.domain.vinculosDeMateriaisComFornecedoras.dto.request.NovoVinculoComFornecedorasDTO;
import br.com.graficaplantao.rest.api.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ConversaoDeCompraService {

    @Autowired
    private ConversaoDeCompraRepository conversaoDeCompraRepository;


    @Transactional
    public void atualizarConversoesDeCompra(List<AtualizacaoConversaoDeCompraDTO> listaAtualizadaDTO, VinculoMaterialComFornecedora vinculo) {

        List<ConversaoDeCompra> itensParaRemover = new ArrayList<>();

        for (var item : vinculo.getConversaoDeCompras()) {
            var undCompra = item.getUndCompra();

            if (!isUndCompraPresent(undCompra, listaAtualizadaDTO)) {
                System.out.println(undCompra);
                itensParaRemover.add(item);
            }
        }

        vinculo.getConversaoDeCompras().removeAll(itensParaRemover);
        conversaoDeCompraRepository.deleteAll(itensParaRemover);

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

    private boolean isUndCompraPresent(Unidade undCompra, List<AtualizacaoConversaoDeCompraDTO> listaAtualizadaDTO) {

        return listaAtualizadaDTO.stream()
                .anyMatch(dto -> dto.undCompra() == undCompra);
    }


    public BigDecimal converterParaUndPadrao(ItemTransacaoEntrada item, Fornecedora fornecedora, Material material) {
        var undPadrao = material.getCategoria().getUndPadrao();
        var undCompra = item.getUndCom();
        BigDecimal qtdeComprada = item.getQuantCom();

        if (undCompra == undPadrao) {
            return qtdeComprada;
        }

        VinculoMaterialComFornecedora vinculoMaterialComFornecedora = material.getFornecedorasVinculadas().stream()
                .filter(vinculo -> vinculo.getFornecedora().equals(fornecedora))
                .findFirst()
                .orElseThrow(() -> new ValidacaoException("Material precisa estar vinculada a uma Fornecedora"));

        ConversaoDeCompra conversaoDeCompra = vinculoMaterialComFornecedora.getConversaoDeCompras().stream()
                .filter(conversao -> conversao.getUndCompra().equals(undCompra))
                .findFirst()
                .orElseThrow(() -> new ValidacaoException("Conversão de " + undPadrao + " para " + undCompra + "não encontrada"));

        BigDecimal fatorConversao = conversaoDeCompra.getFatorDeConversao();

        return qtdeComprada.multiply(fatorConversao);
    }

    public BigDecimal converterParaUndPadrao(AtualizacaoItemTransacaoEntradaDTO item, Fornecedora fornecedora, Material material) {
        var undPadrao = material.getCategoria().getUndPadrao();
        var undCompra = item.undCom();
        BigDecimal qtdeComprada = item.quantCom();

        if (undCompra == undPadrao) {
            return qtdeComprada;
        }

        VinculoMaterialComFornecedora vinculoMaterialComFornecedora = material.getFornecedorasVinculadas().stream()
                .filter(vinculo -> vinculo.getFornecedora().equals(fornecedora))
                .findFirst()
                .orElseThrow(() -> new ValidacaoException("Material precisa estar vinculada a uma Fornecedora"));

        ConversaoDeCompra conversaoDeCompra = vinculoMaterialComFornecedora.getConversaoDeCompras().stream()
                .filter(conversao -> conversao.getUndCompra().equals(undCompra))
                .findFirst()
                .orElseThrow(() -> new ValidacaoException("Conversão de " + undPadrao + " para " + undCompra + " não encontrada"));

        BigDecimal fatorConversao = conversaoDeCompra.getFatorDeConversao();

        return qtdeComprada.multiply(fatorConversao);
    }
}
