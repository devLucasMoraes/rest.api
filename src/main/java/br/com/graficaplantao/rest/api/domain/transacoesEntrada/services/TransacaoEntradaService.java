package br.com.graficaplantao.rest.api.domain.transacoesEntrada.services;

import br.com.graficaplantao.rest.api.domain.fornecedoras.FornecedoraRepository;
import br.com.graficaplantao.rest.api.domain.materiais.MaterialRepository;
import br.com.graficaplantao.rest.api.domain.transacoesEntrada.TransacaoEntrada;
import br.com.graficaplantao.rest.api.domain.transacoesEntrada.TransacaoEntradaRepository;
import br.com.graficaplantao.rest.api.domain.transacoesEntrada.dto.request.AtualizacaoTransacaoEntradaDTO;
import br.com.graficaplantao.rest.api.domain.transacoesEntrada.dto.request.NovaTransacaoEntradaDTO;
import br.com.graficaplantao.rest.api.domain.transacoesEntrada.dto.response.DetalhamentoTransacaoEntradaDTO;
import br.com.graficaplantao.rest.api.domain.transacoesEntrada.dto.response.ListagemTransacaoEntradaDTO;
import br.com.graficaplantao.rest.api.domain.transacoesEntrada.itensTransacoesEntrada.ItemTransacaoEntrada;
import br.com.graficaplantao.rest.api.domain.transacoesEntrada.itensTransacoesEntrada.ItemTransacaoEntradaRepository;
import br.com.graficaplantao.rest.api.domain.transportadoras.TransportadoraRepository;
import br.com.graficaplantao.rest.api.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;

@Service
public class TransacaoEntradaService {

    @Autowired
    private TransacaoEntradaRepository transacaoEntradaRepository;

    @Autowired
    private TransportadoraRepository transportadoraRepository;

    @Autowired
    private FornecedoraRepository fornecedoraRepository;

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private ItemTransacaoEntradaRepository itemTransacaoEntradaRepository;

    public DetalhamentoTransacaoEntradaDTO create(NovaTransacaoEntradaDTO dados) {
        if (!transportadoraRepository.existsById(dados.transportadora_id())) {
            throw new ValidacaoException("Id da transportadora informada não existe");
        }
        if (!fornecedoraRepository.existsById(dados.fornecedora_id())) {
            throw new ValidacaoException("Id da fornecedora informada não existe");
        }

        var transportadora = transportadoraRepository.getReferenceById(dados.transportadora_id());
        var fornecedora = fornecedoraRepository.getReferenceById(dados.fornecedora_id());

        var transacaoEntrada = new TransacaoEntrada(
                null,
                dados.nfe(),
                dados.data_emissao(),
                dados.data_recebimento(),
                new BigDecimal(0),
                dados.valor_frete(),
                new BigDecimal(0),
                dados.obs(),
                transportadora,
                fornecedora,
                new ArrayList<>()
        );

        for (var item : dados.itens()) {
            if (!materialRepository.existsById(item.materiais_id())) {
                throw new ValidacaoException("Id do material informado não existe");
            }

            var material = materialRepository.getReferenceById(item.materiais_id());

            var itemTransacaoEntrada = new ItemTransacaoEntrada(
                    null,
                    material,
                    transacaoEntrada,
                    item.und_com(),
                    item.quant_com(),
                    item.valor_unt_com(),
                    item.valor_ipi(),
                    item.obs()
            );
            transacaoEntrada.adicionarItem(itemTransacaoEntrada);
        }

        transacaoEntradaRepository.save(transacaoEntrada);
        return new  DetalhamentoTransacaoEntradaDTO(transacaoEntrada);
    }

    public Page<ListagemTransacaoEntradaDTO> getAll(Pageable pageable) {
        return transacaoEntradaRepository.findAll(pageable).map(transacaoEntrada -> new ListagemTransacaoEntradaDTO(transacaoEntrada));
    }

    public DetalhamentoTransacaoEntradaDTO getById(Long id) {
        var transacaoEntrada = transacaoEntradaRepository.getReferenceById(id);
        return new DetalhamentoTransacaoEntradaDTO(transacaoEntrada);
    }

    public DetalhamentoTransacaoEntradaDTO updateById(AtualizacaoTransacaoEntradaDTO dados) {
        var transacaoEntrada = transacaoEntradaRepository.getReferenceById(dados.id());
        var transportadora = transportadoraRepository.getReferenceById(dados.transportadora_id());
        var fornecedora = fornecedoraRepository.getReferenceById(dados.fornecedora_id());

        var itensParaAtualizar = new ArrayList<ItemTransacaoEntrada>();
        var itensOriginais = transacaoEntrada.getItens();

        for (var itemAtualizado : dados.itens()) {
            var material = materialRepository.getReferenceById(itemAtualizado.materiais_id());
            var itemTransacaoEntrada = itensOriginais.stream()
                    .filter(item -> item.getId().equals(itemAtualizado.id()))
                    .findFirst()
                    .orElseThrow(() -> new ValidacaoException("Id do item de transação de entrada informado não existe"));
            itemTransacaoEntrada.update(itemAtualizado, material);
            itensParaAtualizar.add(itemTransacaoEntrada);
        }

        transacaoEntrada.update(dados, transportadora, fornecedora, itensParaAtualizar);

        // Remove o item caso ele não tenha sido atualizado
        itensOriginais.forEach(item -> {
            if (!itensParaAtualizar.contains(item)) {
                transacaoEntrada.removerItem(item);
                itemTransacaoEntradaRepository.delete(item);
            }
        });

        transacaoEntradaRepository.save(transacaoEntrada);

        return new DetalhamentoTransacaoEntradaDTO(transacaoEntrada);
    }

    public void deleteById(Long id) {
        transacaoEntradaRepository.deleteById(id);
    }
}
