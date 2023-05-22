package br.com.graficaplantao.rest.api.domain.materiais.vinculosComFornecedoras.services;

import br.com.graficaplantao.rest.api.domain.fornecedoras.services.FornecedoraService;
import br.com.graficaplantao.rest.api.domain.materiais.Material;
import br.com.graficaplantao.rest.api.domain.materiais.vinculosComFornecedoras.VinculoComFornecedoras;
import br.com.graficaplantao.rest.api.domain.materiais.vinculosComFornecedoras.dto.request.AtualizacaoVinculoComFornecedorasDTO;
import br.com.graficaplantao.rest.api.domain.materiais.vinculosComFornecedoras.dto.request.NovoVinculoComFornecedorasDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VinculoComFornecedorasService {

    @Autowired
    private FornecedoraService fornecedoraService;

    public List<VinculoComFornecedoras> criarListaDeVinculoComFornecedorasAtualizada(List<AtualizacaoVinculoComFornecedorasDTO> listaAtualizadaDTO, Material material) {
        List<VinculoComFornecedoras> listaVinculos = new ArrayList<>();

        for (var vinculoAtualizado : listaAtualizadaDTO) {
            var fornecedora = fornecedoraService.getEntityById(vinculoAtualizado.fornecedora_id());
            var vinculoExistente = material.getFornecedorasVinculadas().stream()
                    .filter(item -> item.getId().equals(vinculoAtualizado.id()))
                    .findFirst();

            VinculoComFornecedoras vinculo;
            if (vinculoExistente.isPresent()) {
                vinculo = vinculoExistente.get();
                vinculo.update(vinculoAtualizado,fornecedora);
                vinculo.setMaterial(material);
            } else {
                vinculo = new VinculoComFornecedoras(
                        null,
                        vinculoAtualizado.codProd(),
                        material,
                        fornecedora
                );
            }
            listaVinculos.add(vinculo);
        }

        return listaVinculos;
    }

    public void adicionarListaDeVinculoComFornecedoras(List<NovoVinculoComFornecedorasDTO> listaNovosVinculos, Material material) {

        for (var novoVinculo : listaNovosVinculos) {
            var fornecedora = fornecedoraService.getEntityById(novoVinculo.fornecedora_id());

            var vinculo = new VinculoComFornecedoras(
                    null,
                    novoVinculo.codProd(),
                    material,
                    fornecedora
            );

            material.adicionarVinculo(vinculo);
        }

    }
}
