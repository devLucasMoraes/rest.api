package br.com.graficaplantao.rest.api.controllers;

import br.com.graficaplantao.rest.api.domain.categorias.dto.request.AtualizacaoCategoriaDTO;
import br.com.graficaplantao.rest.api.domain.categorias.dto.request.NovaCategoriaDTO;
import br.com.graficaplantao.rest.api.domain.categorias.dto.response.DetalhamentoCategoriaDTO;
import br.com.graficaplantao.rest.api.domain.categorias.dto.response.ListagemCategoriaDTO;
import br.com.graficaplantao.rest.api.domain.categorias.services.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<DetalhamentoCategoriaDTO> create(@RequestBody @Valid NovaCategoriaDTO dados, UriComponentsBuilder componentsBuilder) {
        var dto = categoriaService.crate(dados);
        var uri = componentsBuilder.path("/categorias/{id}").buildAndExpand(dto.id()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping
    public ResponseEntity<Page<ListagemCategoriaDTO>> getAll(Pageable pageable) {
        var page = categoriaService.getAll(pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhamentoCategoriaDTO> getById(@PathVariable Long id){
        var dto = categoriaService.getById(id);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<DetalhamentoCategoriaDTO> updateById(@RequestBody @Valid AtualizacaoCategoriaDTO dados) {
        var dto = categoriaService.updateById(dados);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        categoriaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
