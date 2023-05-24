package br.com.graficaplantao.rest.api.domain.materiais.vinculosComFornecedoras.services;

import br.com.graficaplantao.rest.api.domain.fornecedoras.services.FornecedoraService;
import br.com.graficaplantao.rest.api.domain.materiais.Material;
import br.com.graficaplantao.rest.api.domain.materiais.vinculosComFornecedoras.VinculoComFornecedoras;
import br.com.graficaplantao.rest.api.domain.materiais.vinculosComFornecedoras.VinculoComFornecedorasRepository;
import br.com.graficaplantao.rest.api.domain.materiais.vinculosComFornecedoras.dto.request.AtualizacaoVinculoComFornecedorasDTO;
import br.com.graficaplantao.rest.api.domain.materiais.vinculosComFornecedoras.dto.request.NovoVinculoComFornecedorasDTO;
import br.com.graficaplantao.rest.api.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VinculoComFornecedorasService {

    @Autowired
    private FornecedoraService fornecedoraService;

    @Autowired
    private VinculoComFornecedorasRepository vinculoComFornecedorasRepository;

    public List<VinculoComFornecedoras> criarListaDeVinculoComFornecedorasAtualizada(List<AtualizacaoVinculoComFornecedorasDTO> listaAtualizadaDTO, Material material) {
        List<VinculoComFornecedoras> listaVinculos = new ArrayList<>();

        for (var vinculoAtualizado : listaAtualizadaDTO) {
            var fornecedora = fornecedoraService.getEntityById(vinculoAtualizado.idFornecedora());
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
            var fornecedora = fornecedoraService.getEntityById(novoVinculo.idFornecedora());

            var vinculo = new VinculoComFornecedoras(
                    null,
                    novoVinculo.codProd(),
                    material,
                    fornecedora
            );

            material.adicionarVinculo(vinculo);
        }

    }

    public Long getMaterialId(String codProd) {
        var vinculo = vinculoComFornecedorasRepository.getReferenceByCodProd(codProd);
        if (vinculo == null) {
            throw new ValidacaoException("Vinculo não encontrado");
        }
        return vinculo.getMaterial().getId();
    }
}
