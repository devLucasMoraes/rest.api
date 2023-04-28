package br.com.graficaplantao.rest.api.controllers;

import br.com.graficaplantao.rest.api.domain.materiais.dto.request.AtualizacaoMaterialDTO;
import br.com.graficaplantao.rest.api.domain.materiais.dto.request.NovoMaterialDTO;
import br.com.graficaplantao.rest.api.domain.materiais.dto.response.DetalhamentoMaterialDTO;
import br.com.graficaplantao.rest.api.domain.materiais.dto.response.ListagemMateriaisDTO;
import br.com.graficaplantao.rest.api.domain.materiais.services.MaterialService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/materiais")
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhamentoMaterialDTO> create(@RequestBody @Valid NovoMaterialDTO dados, UriComponentsBuilder componentsBuilder) {
        var dto = materialService.crate(dados);
        var uri = componentsBuilder.path("/materiais/{id}").buildAndExpand(dto.id()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping
    public ResponseEntity<Page<ListagemMateriaisDTO>> getAll(Pageable pageable) {
        var page = materialService.getAll(pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhamentoMaterialDTO> getById(@PathVariable Long id) {
        var dto = materialService.getById(id);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<DetalhamentoMaterialDTO> updateById(@RequestBody @Valid AtualizacaoMaterialDTO dados) {
        var dto = materialService.updateById(dados);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        materialService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
