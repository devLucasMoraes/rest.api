package br.com.graficaplantao.rest.api.domain.categorias.services;

import br.com.graficaplantao.rest.api.domain.categorias.Categoria;
import br.com.graficaplantao.rest.api.domain.categorias.CategoriaRepository;
import br.com.graficaplantao.rest.api.domain.categorias.dto.request.AtualizacaoCategoriaDTO;
import br.com.graficaplantao.rest.api.domain.categorias.dto.request.NovaCategoriaDTO;
import br.com.graficaplantao.rest.api.domain.categorias.dto.response.DetalhamentoCategoriaDTO;
import br.com.graficaplantao.rest.api.domain.categorias.dto.response.ListagemCategoriaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;
    public DetalhamentoCategoriaDTO crate(NovaCategoriaDTO dados) {
        var categoria = new Categoria(dados);
        categoriaRepository.save(categoria);
        return new DetalhamentoCategoriaDTO(categoria);
    }

    public Page<ListagemCategoriaDTO> getAll(Pageable pageable) {
        return categoriaRepository.findAll(pageable).map(ListagemCategoriaDTO::new);
    }

    public DetalhamentoCategoriaDTO getById(Long id) {
        var categoria = categoriaRepository.getReferenceById(id);
        return new DetalhamentoCategoriaDTO(categoria);
    }

    public DetalhamentoCategoriaDTO updateById(AtualizacaoCategoriaDTO dados) {
        var categoria = categoriaRepository.getReferenceById(dados.id());
        categoria.update(dados);
        return new DetalhamentoCategoriaDTO(categoria);
    }

    public void deleteById(Long id) {
        categoriaRepository.deleteById(id);
    }

}
