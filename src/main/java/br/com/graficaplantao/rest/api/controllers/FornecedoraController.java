package br.com.graficaplantao.rest.api.controllers;

import br.com.graficaplantao.rest.api.domain.fornecedoras.dto.request.AtualizacaoFornecedoraDTO;
import br.com.graficaplantao.rest.api.domain.fornecedoras.dto.request.NovaFornecedoraDTO;
import br.com.graficaplantao.rest.api.domain.fornecedoras.dto.response.DetalhamentoFornecedoraDTO;
import br.com.graficaplantao.rest.api.domain.fornecedoras.dto.response.ListagemFornecedoraDTO;
import br.com.graficaplantao.rest.api.domain.fornecedoras.services.FornecedoraService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/fornecedoras")
public class FornecedoraController {

    @Autowired
    private FornecedoraService fornecedoraService;

    @PostMapping
    public ResponseEntity<DetalhamentoFornecedoraDTO> create(@RequestBody @Valid NovaFornecedoraDTO dados, UriComponentsBuilder componentsBuilder) {
        var dto = fornecedoraService.create(dados);
        var uri = componentsBuilder.path("/fornecedoras/{id}").buildAndExpand(dto.id()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping
    public ResponseEntity<Page<ListagemFornecedoraDTO>> getAll(Pageable pageable, @RequestParam(defaultValue = "") String nomeFantasia) {
        var page = fornecedoraService.getAll(pageable, nomeFantasia);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhamentoFornecedoraDTO> getById(@PathVariable Long id){
        var dto = fornecedoraService.getById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("search/cnpj/{cnpj}")
    public ResponseEntity<DetalhamentoFornecedoraDTO> getByCnpj(@PathVariable String cnpj){
        var dto = fornecedoraService.getByCnpj(cnpj);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<DetalhamentoFornecedoraDTO> updateById(@RequestBody @Valid AtualizacaoFornecedoraDTO dados) {
        var dto = fornecedoraService.updateById(dados);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        fornecedoraService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
