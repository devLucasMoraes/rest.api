package br.com.graficaplantao.rest.api.domain.categorias.services;

import br.com.graficaplantao.rest.api.domain.categorias.Categoria;
import br.com.graficaplantao.rest.api.domain.categorias.CategoriaRepository;
import br.com.graficaplantao.rest.api.domain.categorias.dto.request.AtualizacaoCategoriaDTO;
import br.com.graficaplantao.rest.api.domain.categorias.dto.request.NovaCategoriaDTO;
import br.com.graficaplantao.rest.api.domain.categorias.dto.response.DetalhamentoCategoriaDTO;
import br.com.graficaplantao.rest.api.domain.categorias.dto.response.ListagemCategoriaDTO;
import br.com.graficaplantao.rest.api.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Transactional
    public DetalhamentoCategoriaDTO create(NovaCategoriaDTO dados) {
        var categoria = new Categoria(dados);
        categoriaRepository.save(categoria);
        return new DetalhamentoCategoriaDTO(categoria);
    }

    public Page<ListagemCategoriaDTO> getAll(Pageable pageable, String nome) {
        return categoriaRepository.findByNomeContainingIgnoreCase(nome, pageable).map(ListagemCategoriaDTO::new);
    }

    public DetalhamentoCategoriaDTO getById(Long id) {
        var categoria = categoriaRepository.getReferenceById(id);
        return new DetalhamentoCategoriaDTO(categoria);
    }

    @Transactional
    public DetalhamentoCategoriaDTO updateById(AtualizacaoCategoriaDTO dados) {
        var categoria = categoriaRepository.getReferenceById(dados.id());
        categoria.update(dados);
        return new DetalhamentoCategoriaDTO(categoria);
    }

    @Transactional
    public void deleteById(Long id) {
        categoriaRepository.deleteById(id);
    }

    public Categoria getEntityById(Long id) {
        if(!categoriaRepository.existsById(id)) {
            throw new ValidacaoException("Id da categoria informada não existe");
        }
        return categoriaRepository.getReferenceById(id);
    }
}
