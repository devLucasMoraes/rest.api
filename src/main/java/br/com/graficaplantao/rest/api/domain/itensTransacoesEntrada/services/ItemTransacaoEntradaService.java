package br.com.graficaplantao.rest.api.domain.itensTransacoesEntrada.services;

import br.com.graficaplantao.rest.api.domain.itensTransacoesEntrada.ItemTransacaoEntrada;
import br.com.graficaplantao.rest.api.domain.itensTransacoesEntrada.ItemTransacaoEntradaRepository;
import br.com.graficaplantao.rest.api.domain.itensTransacoesEntrada.dto.request.AtualizacaoItemTransacaoEntradaDTO;
import br.com.graficaplantao.rest.api.domain.itensTransacoesEntrada.dto.request.NovoItemTransacaoEntradaDTO;
import br.com.graficaplantao.rest.api.domain.materiais.Material;
import br.com.graficaplantao.rest.api.domain.materiais.services.MaterialService;
import br.com.graficaplantao.rest.api.domain.transacoesEntrada.TransacaoEntrada;
import br.com.graficaplantao.rest.api.exception.ValidacaoException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemTransacaoEntradaService {

    @Autowired
    MaterialService materialService;

    @Autowired
    ItemTransacaoEntradaRepository itemTransacaoEntradaRepository;

    @Transactional
    public void atualizarItensTransacaoEntrada(TransacaoEntrada transacaoEntrada, List<AtualizacaoItemTransacaoEntradaDTO> listaAtualizadaDTO) {

        List<ItemTransacaoEntrada> itensParaRemover = new ArrayList<>();

        for (var item : transacaoEntrada.getItens()) {
            var material = item.getMaterial();

            if (!isMaterialPresent(material.getId(), listaAtualizadaDTO)) {
                itensParaRemover.add(item);
            }
        }

        transacaoEntrada.getItens().removeAll(itensParaRemover);
        itemTransacaoEntradaRepository.deleteAll(itensParaRemover);

        for (var itemAtualizado : listaAtualizadaDTO) {

            var material = materialService.getEntityById(itemAtualizado.idMaterial());

            validarUnidadeMedida(itemAtualizado, material);

            ItemTransacaoEntrada item = transacaoEntrada.getItens().stream()
                    .filter(itemJaExistente -> itemJaExistente.getMaterial().getId().equals(itemAtualizado.idMaterial()))
                    .findFirst()
                    .orElseGet((() -> criarNovoItem(new NovoItemTransacaoEntradaDTO(itemAtualizado), material, transacaoEntrada)));

            item.update(itemAtualizado, material, transacaoEntrada);


        }

    }

    @Transactional
    public void adicionarItensTransacaoEntrada(List<NovoItemTransacaoEntradaDTO> listaNovosItens, TransacaoEntrada transacaoEntrada) {

        for (var novoItem : listaNovosItens) {
            var material = materialService.getEntityById(novoItem.idMaterial());

            validarUnidadeMedida(novoItem, material);

            criarNovoItem(novoItem, material, transacaoEntrada);

        }

    }

    private boolean isMaterialPresent(long materialId, List<AtualizacaoItemTransacaoEntradaDTO> listaAtualizadaDTO) {
        return listaAtualizadaDTO.stream()
                .anyMatch(dto -> dto.idMaterial() == materialId);
    }

    private void validarUnidadeMedida(AtualizacaoItemTransacaoEntradaDTO item, Material material) {
        String undPadrao = material.getCategoria().getUndPadrao().toString();
        String undCom = item.undCom().toString();

        if (!undPadrao.equals(undCom)) {
            throw new ValidacaoException("A unidade de compra não possui conversão para a unidade padrão");
        }
    }

    private void validarUnidadeMedida(NovoItemTransacaoEntradaDTO item, Material material) {
        String undPadrao = material.getCategoria().getUndPadrao().toString();
        String undCom = item.undCom().toString();

        if (!undPadrao.equals(undCom)) {
            throw new ValidacaoException("A unidade de compra não possui conversão para a unidade padrão");
        }
    }

    private ItemTransacaoEntrada criarNovoItem(@Valid NovoItemTransacaoEntradaDTO item, Material material, TransacaoEntrada transacaoEntrada) {
        ItemTransacaoEntrada novoItem =  new ItemTransacaoEntrada(
                null,
                material,
                transacaoEntrada,
                item.undCom(),
                item.quantCom(),
                item.valorUntCom(),
                item.valorIpi(),
                item.obs()
        );

        transacaoEntrada.adicionarItem(novoItem);

        return novoItem;
    }
}
