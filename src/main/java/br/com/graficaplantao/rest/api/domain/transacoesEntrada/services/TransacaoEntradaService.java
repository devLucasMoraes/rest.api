package br.com.graficaplantao.rest.api.domain.transacoesEntrada.services;

import br.com.graficaplantao.rest.api.domain.fornecedoras.services.FornecedoraService;
import br.com.graficaplantao.rest.api.domain.transacoesEntrada.TransacaoEntrada;
import br.com.graficaplantao.rest.api.domain.transacoesEntrada.TransacaoEntradaRepository;
import br.com.graficaplantao.rest.api.domain.transacoesEntrada.dto.request.AtualizacaoTransacaoEntradaCompletaDTO;
import br.com.graficaplantao.rest.api.domain.transacoesEntrada.dto.request.NovaTransacaoEntradaDTO;
import br.com.graficaplantao.rest.api.domain.transacoesEntrada.dto.response.ListagemTransacaoEntradaDTO;
import br.com.graficaplantao.rest.api.domain.transacoesEntrada.dto.response.TransacaoEntradaResponseDTO;
import br.com.graficaplantao.rest.api.domain.transacoesEntrada.itensTransacoesEntrada.services.ItemTransacaoEntradaService;
import br.com.graficaplantao.rest.api.domain.transportadoras.services.TransportadoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;

@Service
public class TransacaoEntradaService {

    @Autowired
    private TransacaoEntradaRepository transacaoEntradaRepository;

    @Autowired
    private TransportadoraService transportadoraService;

    @Autowired
    private FornecedoraService fornecedoraService;

    @Autowired
    private ItemTransacaoEntradaService itemTransacaoEntradaService;

    @Transactional
    public TransacaoEntradaResponseDTO create(NovaTransacaoEntradaDTO dados) {

        var transportadora = transportadoraService.getEntityById(dados.transportadora_id());
        var fornecedora = fornecedoraService.getEntityById(dados.fornecedora_id());

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

        itemTransacaoEntradaService.adicionarListaItensTransacaoEntrada(dados.itens(), transacaoEntrada);

        transacaoEntradaRepository.save(transacaoEntrada);
        return new TransacaoEntradaResponseDTO(transacaoEntrada);
    }

    public Page<ListagemTransacaoEntradaDTO> getAll(Pageable pageable) {
        return transacaoEntradaRepository.findAll(pageable).map(transacaoEntrada -> new ListagemTransacaoEntradaDTO(transacaoEntrada));
    }

    public TransacaoEntradaResponseDTO getById(Long id) {
        var transacaoEntrada = transacaoEntradaRepository.getReferenceById(id);
        return new TransacaoEntradaResponseDTO(transacaoEntrada);
    }

    @Transactional
    public TransacaoEntradaResponseDTO updateById(Long id, AtualizacaoTransacaoEntradaCompletaDTO atualizacaoDTO) {
        var transacaoEntrada = transacaoEntradaRepository.getReferenceById(id);

        // Atualiza os campos da transação de entrada com base no DTO de atualização
        transacaoEntrada.update(
                atualizacaoDTO,
                transportadoraService.getEntityById(atualizacaoDTO.transportadora_id()),
                fornecedoraService.getEntityById(atualizacaoDTO.fornecedora_id()),
                itemTransacaoEntradaService.criarListaItensTransacaoEntradaAtualizada(atualizacaoDTO.itens(), transacaoEntrada)
        );

        // Salva as atualizações no banco de dados
        transacaoEntradaRepository.save(transacaoEntrada);

        // Retorna o DTO de resposta com os dados da transação de entrada atualizada
        return new TransacaoEntradaResponseDTO(transacaoEntrada);
    }

    @Transactional
    public void deleteById(Long id) {
        transacaoEntradaRepository.deleteById(id);
    }
}
