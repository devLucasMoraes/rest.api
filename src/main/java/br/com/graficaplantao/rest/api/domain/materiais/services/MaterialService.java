package br.com.graficaplantao.rest.api.domain.materiais.services;

import br.com.graficaplantao.rest.api.domain.categorias.services.CategoriaService;
import br.com.graficaplantao.rest.api.domain.materiais.Material;
import br.com.graficaplantao.rest.api.domain.materiais.MaterialRepository;
import br.com.graficaplantao.rest.api.domain.materiais.dto.request.AtualizacaoMaterialDTO;
import br.com.graficaplantao.rest.api.domain.materiais.dto.request.NovoMaterialDTO;
import br.com.graficaplantao.rest.api.domain.materiais.dto.response.DetalhamentoMaterialDTO;
import br.com.graficaplantao.rest.api.domain.materiais.dto.response.ListagemMateriaisDTO;
import br.com.graficaplantao.rest.api.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MaterialService {

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private CategoriaService categoriaService;

    @Transactional
    public DetalhamentoMaterialDTO crate(NovoMaterialDTO dados) {
        if(dados.valor_unt() == null) {
            throw new ValidacaoException("valor unitario nao pode ser nulo");
        }

        var categoria = categoriaService.getEntityById(dados.categorias_id());
        var material = new Material(null, dados.cod_prod(), dados.descricao(), dados.valor_unt(), categoria);
        materialRepository.save(material);

        return new  DetalhamentoMaterialDTO(material);
    }

    public Page<ListagemMateriaisDTO> getAll(Pageable pageable) {
        return materialRepository.findAll(pageable).map(ListagemMateriaisDTO::new);
    }

    public DetalhamentoMaterialDTO getById(Long id) {
        var material = materialRepository.getReferenceById(id);
        return new DetalhamentoMaterialDTO(material);
    }

    @Transactional
    public DetalhamentoMaterialDTO updateById(AtualizacaoMaterialDTO dados) {
        var material = materialRepository.getReferenceById(dados.id());
        var categoria = categoriaService.getEntityById((dados.categorias_id()));
        material.update(dados, categoria);

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
