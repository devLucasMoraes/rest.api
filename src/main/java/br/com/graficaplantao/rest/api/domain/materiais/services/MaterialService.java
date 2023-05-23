package br.com.graficaplantao.rest.api.domain.materiais.services;

import br.com.graficaplantao.rest.api.domain.categorias.services.CategoriaService;
import br.com.graficaplantao.rest.api.domain.materiais.Material;
import br.com.graficaplantao.rest.api.domain.materiais.MaterialRepository;
import br.com.graficaplantao.rest.api.domain.materiais.dto.request.AtualizacaoMaterialDTO;
import br.com.graficaplantao.rest.api.domain.materiais.dto.request.NovoMaterialDTO;
import br.com.graficaplantao.rest.api.domain.materiais.dto.response.DetalhamentoMaterialDTO;
import br.com.graficaplantao.rest.api.domain.materiais.dto.response.ListagemMateriaisDTO;
import br.com.graficaplantao.rest.api.domain.materiais.vinculosComFornecedoras.VinculoComFornecedoras;
import br.com.graficaplantao.rest.api.domain.materiais.vinculosComFornecedoras.services.VinculoComFornecedorasService;
import br.com.graficaplantao.rest.api.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class MaterialService {

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private VinculoComFornecedorasService vinculoComFornecedorasService;

    @Transactional
    public DetalhamentoMaterialDTO create(NovoMaterialDTO dados) {

        var categoria = categoriaService.getEntityById(dados.idCategoria());
        var material = new Material(null, dados.descricao(), dados.valorUnt(), categoria, new ArrayList<>());

        if (dados.fornecedorasVinculadas() != null && !dados.fornecedorasVinculadas().isEmpty()) {
            vinculoComFornecedorasService.adicionarListaDeVinculoComFornecedoras(dados.fornecedorasVinculadas(), material);
        }

        materialRepository.save(material);

        return new  DetalhamentoMaterialDTO(material);
    }

    public Page<ListagemMateriaisDTO> getAll(Pageable pageable, String descricao) {
        return materialRepository.findByDescricaoContainingIgnoreCase(descricao, pageable).map(ListagemMateriaisDTO::new);
    }

    public DetalhamentoMaterialDTO getById(Long id) {
        var material = materialRepository.getReferenceById(id);
        return new DetalhamentoMaterialDTO(material);
    }

    @Transactional
    public DetalhamentoMaterialDTO updateById(Long id,AtualizacaoMaterialDTO dados) {
        var material = materialRepository.getReferenceById(id);

        var categoria = categoriaService.getEntityById((dados.idCategoria()));

        List<VinculoComFornecedoras> vinculosAtualizados = new ArrayList<>();

        if (dados.fornecedorasVinculadas() == null) {
            material.getFornecedorasVinculadas().clear();
        }

        if (dados.fornecedorasVinculadas() != null) {
            vinculosAtualizados = vinculoComFornecedorasService
                    .criarListaDeVinculoComFornecedorasAtualizada(dados.fornecedorasVinculadas(), material);
        }

        material.update(
                dados,
                categoria,
                vinculosAtualizados
        );



        materialRepository.save(material);

        return new DetalhamentoMaterialDTO(material);
    }

    @Transactional
    public void deleteById(Long id) {
        materialRepository.deleteById(id);
    }

    public Material getEntityById(Long id) {
        if (!materialRepository.existsById(id)) {
            throw new ValidacaoException("Id do material informado não existe");
        }
        return materialRepository.getReferenceById(id);
    }
}
