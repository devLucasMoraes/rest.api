package br.com.graficaplantao.rest.api.domain.itensTransacoesEntrada.services;

import br.com.graficaplantao.rest.api.domain.itensTransacoesEntrada.ItemTransacaoEntrada;
import br.com.graficaplantao.rest.api.domain.itensTransacoesEntrada.ItemTransacaoEntradaRepository;
import br.com.graficaplantao.rest.api.domain.itensTransacoesEntrada.dto.request.AtualizacaoItemTransacaoEntradaDTO;
import br.com.graficaplantao.rest.api.domain.itensTransacoesEntrada.dto.request.NovoItemTransacaoEntradaDTO;
import br.com.graficaplantao.rest.api.domain.materiais.services.MaterialService;
import br.com.graficaplantao.rest.api.domain.transacoesEntrada.TransacaoEntrada;
import br.com.graficaplantao.rest.api.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ItemTransacaoEntradaService {

    @Autowired
    MaterialService materialService;

    @Autowired
    ItemTransacaoEntradaRepository itemTransacaoEntradaRepository;

    @Transactional
    public List<ItemTransacaoEntrada> criarListaItensTransacaoEntradaAtualizada(List<AtualizacaoItemTransacaoEntradaDTO> listaAtualizadaDTO, TransacaoEntrada transacaoEntrada) {
        List<ItemTransacaoEntrada> listaItens = new ArrayList<>();
        List<ItemTransacaoEntrada> itensRemover = new ArrayList<>();

        if(listaAtualizadaDTO.isEmpty()) {
            throw new ValidacaoException("Lista de itens não pode estar vazio");
        }

        for (var item : transacaoEntrada.getItens()) {
            var material = item.getMaterial();


            if (!isMaterialPresent(material.getId(), listaAtualizadaDTO)) {
                itensRemover.add(item);
            }
        }

        transacaoEntrada.getItens().removeAll(itensRemover);
        itemTransacaoEntradaRepository.deleteAll(itensRemover);

        for (var itemAtualizado : listaAtualizadaDTO) {
            if(itemAtualizado.quantCom().equals(new BigDecimal(0))) {
                throw new ValidacaoException("quantidade comprada não pode ser zero");
            }
            var material = materialService.getEntityById(itemAtualizado.idMaterial());
            var undPadrao = material.getCategoria().getUndPadrao().toString();
            if(!undPadrao.equals(itemAtualizado.undCom().toString())) {
                throw new ValidacaoException("unidade de compra sem conversao para unidade padrao");
            }
            var itemExistente = transacaoEntrada.getItens().stream()
                    .filter(item -> item.getMaterial().getId().equals(itemAtualizado.idMaterial()))
                    .findFirst();

            ItemTransacaoEntrada item;
            if (itemExistente.isPresent()) {
                item = itemExistente.get();
                item.update(itemAtualizado,material);
                item.setTransacaoEntrada(transacaoEntrada);
            } else {
                item = new ItemTransacaoEntrada(
                        null,
                        material,
                        transacaoEntrada,
                        itemAtualizado.undCom(),
                        itemAtualizado.quantCom(),
                        itemAtualizado.valorUntCom(),
                        itemAtualizado.valorIpi(),
                        itemAtualizado.obs()
                );
            }
            listaItens.add(item);
        }

        return listaItens;
    }

    @Transactional
    public void adicionarListaItensTransacaoEntrada(List<NovoItemTransacaoEntradaDTO> listaNovosItens, TransacaoEntrada transacaoEntrada) {

        if(listaNovosItens.isEmpty()) {
            throw new ValidacaoException("Lista de itens não pode estar vazio");
        }

        for (var novoItem : listaNovosItens) {
            if(novoItem.quantCom().equals(new BigDecimal(0))) {
                throw new ValidacaoException("quantidade comprada não pode ser zero");
            }
            var material = materialService.getEntityById(novoItem.idMaterial());
            var undPadrao = material.getCategoria().getUndPadrao().toString();
            if(!undPadrao.equals(novoItem.undCom().toString())) {
                throw new ValidacaoException("unidade de compra sem conversao para unidade padrao");
            }

            var item = new ItemTransacaoEntrada(
                    null,
                    material,
                    transacaoEntrada,
                    novoItem.undCom(),
                    novoItem.quantCom(),
                    novoItem.valorUntCom(),
                    novoItem.valorIpi(),
                    novoItem.obs()
            );


            transacaoEntrada.adicionarItem(item);
        }

    }

    private boolean isMaterialPresent(long materialId, List<AtualizacaoItemTransacaoEntradaDTO> listaAtualizadaDTO) {
        return listaAtualizadaDTO.stream()
                .anyMatch(dto -> dto.idMaterial() == materialId);
    }
}
