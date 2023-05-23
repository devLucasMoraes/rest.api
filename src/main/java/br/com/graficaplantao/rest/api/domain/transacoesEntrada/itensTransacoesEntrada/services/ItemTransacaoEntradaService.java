package br.com.graficaplantao.rest.api.domain.transacoesEntrada.itensTransacoesEntrada.services;

import br.com.graficaplantao.rest.api.domain.materiais.services.MaterialService;
import br.com.graficaplantao.rest.api.domain.transacoesEntrada.TransacaoEntrada;
import br.com.graficaplantao.rest.api.domain.transacoesEntrada.itensTransacoesEntrada.ItemTransacaoEntrada;
import br.com.graficaplantao.rest.api.domain.transacoesEntrada.itensTransacoesEntrada.dto.request.AtualizacaoItemTransacaoEntradaDTO;
import br.com.graficaplantao.rest.api.domain.transacoesEntrada.itensTransacoesEntrada.dto.request.NovoItemTransacaoEntradaDTO;
import br.com.graficaplantao.rest.api.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ItemTransacaoEntradaService {

    @Autowired
    MaterialService materialService;

    public List<ItemTransacaoEntrada> criarListaItensTransacaoEntradaAtualizada(List<AtualizacaoItemTransacaoEntradaDTO> listaAtualizadaDTO, TransacaoEntrada transacaoEntrada) {
        List<ItemTransacaoEntrada> listaItens = new ArrayList<>();

        for (var itemAtualizado : listaAtualizadaDTO) {
            if(itemAtualizado.quantCom().equals(new BigDecimal(0))) {
                throw new ValidacaoException("quantidade comprada não pode ser zero");
            }
            var material = materialService.getEntityById(itemAtualizado.idMaterial());
            var itemExistente = transacaoEntrada.getItens().stream()
                    .filter(item -> item.getId().equals(itemAtualizado.id()))
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

    public void adicionarListaItensTransacaoEntrada(List<NovoItemTransacaoEntradaDTO> listaNovosItens, TransacaoEntrada transacaoEntrada) {

        for (var novoItem : listaNovosItens) {
            var material = materialService.getEntityById(novoItem.idMaterial());

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
}
