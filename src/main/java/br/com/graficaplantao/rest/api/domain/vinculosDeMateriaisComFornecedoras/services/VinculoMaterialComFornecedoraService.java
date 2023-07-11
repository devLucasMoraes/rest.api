package br.com.graficaplantao.rest.api.domain.vinculosDeMateriaisComFornecedoras.services;

import br.com.graficaplantao.rest.api.domain.conversoesDeCompra.services.ConversaoDeCompraService;
import br.com.graficaplantao.rest.api.domain.fornecedoras.Fornecedora;
import br.com.graficaplantao.rest.api.domain.fornecedoras.services.FornecedoraService;
import br.com.graficaplantao.rest.api.domain.materiais.Material;
import br.com.graficaplantao.rest.api.domain.vinculosDeMateriaisComFornecedoras.VinculoMaterialComFornecedora;
import br.com.graficaplantao.rest.api.domain.vinculosDeMateriaisComFornecedoras.VinculoMaterialComFornecedoraRepository;
import br.com.graficaplantao.rest.api.domain.vinculosDeMateriaisComFornecedoras.dto.request.AtualizacaoVinculoComFornecedorasDTO;
import br.com.graficaplantao.rest.api.domain.vinculosDeMateriaisComFornecedoras.dto.request.NovoVinculoComFornecedorasDTO;
import br.com.graficaplantao.rest.api.exception.ValidacaoException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public void atualizarVinculoComFornecedoras(List<AtualizacaoVinculoComFornecedorasDTO> listaAtualizadaDTO, Material material) {

        List<VinculoMaterialComFornecedora> itensParaRemover = new ArrayList<>();

        for (var item : material.getFornecedorasVinculadas()) {
            var fornecedora = item.getFornecedora();

            if (!isFornecedoraPresent(fornecedora.getId(), listaAtualizadaDTO)) {
                itensParaRemover.add(item);
            }
        }

        material.getFornecedorasVinculadas().removeAll(itensParaRemover);
        vinculoComFornecedorasRepository.deleteAll(itensParaRemover);


        for (var vinculoAtualizado : listaAtualizadaDTO) {

            var fornecedora = fornecedoraService.getEntityById(vinculoAtualizado.idFornecedora());

            VinculoMaterialComFornecedora vinculo = material.getFornecedorasVinculadas().stream()
                    .filter(item -> item.getFornecedora().getId().equals(vinculoAtualizado.idFornecedora()))
                    .findFirst()
                    .orElseGet(() -> criarNovoVinculo(new NovoVinculoComFornecedorasDTO(vinculoAtualizado), material, fornecedora));

            vinculo.update(vinculoAtualizado, fornecedora, material);

            conversaoDeCompraService.atualizarConversoesDeCompra(vinculoAtualizado.conversoesDeCompra(), vinculo);

        }

    }

    @Transactional
    public void adicionarVinculoComFornecedoras(List<NovoVinculoComFornecedorasDTO> listaNovosVinculos, Material material) {

        for (var novoVinculo : listaNovosVinculos) {
            var fornecedora = fornecedoraService.getEntityById(novoVinculo.idFornecedora());

            var vinculo = criarNovoVinculo(novoVinculo,material,fornecedora);

            conversaoDeCompraService.adicionarConversoesDeCompra(novoVinculo.conversoesDeCompra(), vinculo);
        }

    }

    public Long getMaterialId(String codProd) {
        var vinculo = vinculoComFornecedorasRepository.getReferenceByCodProd(codProd);
        if (vinculo == null) {
            throw new ValidacaoException("Vinculo não encontrado");
        }
        return vinculo.getMaterial().getId();
    }

    private VinculoMaterialComFornecedora criarNovoVinculo(@Valid NovoVinculoComFornecedorasDTO vinculo, Material material, Fornecedora fornecedora) {
        VinculoMaterialComFornecedora novoVinculo =  new VinculoMaterialComFornecedora(
                null,
                vinculo.codProd(),
                material,
                fornecedora,
                new ArrayList<>()
        );

        material.adicionarVinculo(novoVinculo);

        return novoVinculo;
    }

    private boolean isFornecedoraPresent(long fornecedoraId, List<AtualizacaoVinculoComFornecedorasDTO> listaAtualizadaDTO) {
        return listaAtualizadaDTO.stream()
                .anyMatch(dto -> dto.idFornecedora() == fornecedoraId);
    }
}
