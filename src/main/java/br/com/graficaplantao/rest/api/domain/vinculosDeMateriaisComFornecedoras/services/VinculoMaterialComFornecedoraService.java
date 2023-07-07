package br.com.graficaplantao.rest.api.domain.vinculosDeMateriaisComFornecedoras.services;

import br.com.graficaplantao.rest.api.domain.conversoesDeCompra.services.ConversaoDeCompraService;
import br.com.graficaplantao.rest.api.domain.fornecedoras.services.FornecedoraService;
import br.com.graficaplantao.rest.api.domain.materiais.Material;
import br.com.graficaplantao.rest.api.domain.vinculosDeMateriaisComFornecedoras.VinculoMaterialComFornecedora;
import br.com.graficaplantao.rest.api.domain.vinculosDeMateriaisComFornecedoras.VinculoMaterialComFornecedoraRepository;
import br.com.graficaplantao.rest.api.domain.vinculosDeMateriaisComFornecedoras.dto.request.AtualizacaoVinculoComFornecedorasDTO;
import br.com.graficaplantao.rest.api.domain.vinculosDeMateriaisComFornecedoras.dto.request.NovoVinculoComFornecedorasDTO;
import br.com.graficaplantao.rest.api.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VinculoMaterialComFornecedoraService {

    @Autowired
    private FornecedoraService fornecedoraService;

    @Autowired
    private ConversaoDeCompraService conversaoDeCompraService;

    @Autowired
    private VinculoMaterialComFornecedoraRepository vinculoComFornecedorasRepository;

    public List<VinculoMaterialComFornecedora> criarListaDeVinculoComFornecedorasAtualizada(List<AtualizacaoVinculoComFornecedorasDTO> listaAtualizadaDTO, Material material) {
        List<VinculoMaterialComFornecedora> listaVinculos = new ArrayList<>();

        for (var vinculoAtualizado : listaAtualizadaDTO) {
            var fornecedora = fornecedoraService.getEntityById(vinculoAtualizado.idFornecedora());
            var vinculoExistente = material.getFornecedorasVinculadas().stream()
                    .filter(item -> item.getFornecedora().getId().equals(vinculoAtualizado.idFornecedora()))
                    .findFirst();

            VinculoMaterialComFornecedora vinculo;
            if (vinculoExistente.isPresent()) {
                vinculo = vinculoExistente.get();
                vinculo.update(vinculoAtualizado,fornecedora);
                vinculo.setMaterial(material);
            } else {
                vinculo = new VinculoMaterialComFornecedora(
                        null,
                        vinculoAtualizado.codProd(),
                        material,
                        fornecedora,
                        new ArrayList<>()
                );


            }
            listaVinculos.add(vinculo);
        }

        return listaVinculos;
    }

    public void adicionarListaDeVinculoComFornecedoras(List<NovoVinculoComFornecedorasDTO> listaNovosVinculos, Material material) {

        for (var novoVinculo : listaNovosVinculos) {
            var fornecedora = fornecedoraService.getEntityById(novoVinculo.idFornecedora());

            var vinculo = new VinculoMaterialComFornecedora(
                    null,
                    novoVinculo.codProd(),
                    material,
                    fornecedora,
                    new ArrayList<>()
            );

            material.adicionarVinculo(vinculo);

            conversaoDeCompraService.adicionarListaDeConversoesDeCompra(novoVinculo.conversoesDeCompra(), vinculo);
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
