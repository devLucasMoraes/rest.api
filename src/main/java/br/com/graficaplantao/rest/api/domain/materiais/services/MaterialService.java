package br.com.graficaplantao.rest.api.domain.materiais.services;

import br.com.graficaplantao.rest.api.domain.categorias.CategoriaRepository;
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

@Service
public class MaterialService {

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public DetalhamentoMaterialDTO crate(NovoMaterialDTO dados) {
        if(!categoriaRepository.existsById(dados.categorias_id())) {
            throw new ValidacaoException("Id da categoria informada não existe");
        }
        if(dados.valor_unt() == null) {
            throw new ValidacaoException("valor unitario nao pode ser nulo");
        }

        var categoria = categoriaRepository.getReferenceById(dados.categorias_id());
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

    public DetalhamentoMaterialDTO updateById(AtualizacaoMaterialDTO dados) {
        var material = materialRepository.getReferenceById(dados.id());
        var categoria = categoriaRepository.getReferenceById((dados.categorias_id()));
        material.update(dados, categoria);

        materialRepository.save(material);

        return new DetalhamentoMaterialDTO(material);
    }

    public void deleteById(Long id) {
        materialRepository.deleteById(id);
    }
}
