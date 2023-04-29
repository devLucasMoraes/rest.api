package br.com.graficaplantao.rest.api.controllers;

import br.com.graficaplantao.rest.api.domain.transportadoras.dto.request.AtualizacaoTransportadoraDTO;
import br.com.graficaplantao.rest.api.domain.transportadoras.dto.request.NovaTransportadoraDTO;
import br.com.graficaplantao.rest.api.domain.transportadoras.dto.response.DetalhamentoTransportadoraDTO;
import br.com.graficaplantao.rest.api.domain.transportadoras.dto.response.ListagemTransportadoraDTO;
import br.com.graficaplantao.rest.api.domain.transportadoras.services.TrasportadoraService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/transportadoras")
public class TransportadoraController {

    @Autowired
    private TrasportadoraService trasportadoraService;

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhamentoTransportadoraDTO> create(@RequestBody @Valid NovaTransportadoraDTO dados, UriComponentsBuilder componentsBuilder) {
        var dto = trasportadoraService.crate(dados);
        var uri = componentsBuilder.path("/fornecedoras/{id}").buildAndExpand(dto.id()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping
    public ResponseEntity<Page<ListagemTransportadoraDTO>> getAll(Pageable pageable) {
        var page = trasportadoraService.getAll(pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhamentoTransportadoraDTO> getById(@PathVariable Long id){
        var dto = trasportadoraService.getById(id);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    @Transactional
    public  ResponseEntity<DetalhamentoTransportadoraDTO> updateById(@RequestBody @Valid AtualizacaoTransportadoraDTO dados) {
        var dto = trasportadoraService.updateById(dados);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        trasportadoraService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
