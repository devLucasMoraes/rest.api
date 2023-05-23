package br.com.graficaplantao.rest.api.controllers;

import br.com.graficaplantao.rest.api.domain.transportadoras.dto.request.AtualizacaoTransportadoraDTO;
import br.com.graficaplantao.rest.api.domain.transportadoras.dto.request.NovaTransportadoraDTO;
import br.com.graficaplantao.rest.api.domain.transportadoras.dto.response.DetalhamentoTransportadoraDTO;
import br.com.graficaplantao.rest.api.domain.transportadoras.dto.response.ListagemTransportadoraDTO;
import br.com.graficaplantao.rest.api.domain.transportadoras.services.TransportadoraService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/transportadoras")
public class TransportadoraController {

    @Autowired
    private TransportadoraService transportadoraService;

    @PostMapping
    public ResponseEntity<DetalhamentoTransportadoraDTO> create(@RequestBody @Valid NovaTransportadoraDTO dados, UriComponentsBuilder componentsBuilder) {
        var dto = transportadoraService.create(dados);
        var uri = componentsBuilder.path("/fornecedoras/{id}").buildAndExpand(dto.id()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping
    public ResponseEntity<Page<ListagemTransportadoraDTO>> getAll(Pageable pageable, @RequestParam(defaultValue = "") String nomeFantasia) {
        var page = transportadoraService.getAll(pageable, nomeFantasia);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhamentoTransportadoraDTO> getById(@PathVariable Long id){
        var dto = transportadoraService.getById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/search/cnpj/{cnpj}")
    public ResponseEntity<DetalhamentoTransportadoraDTO> getByCnpj(@PathVariable String cnpj){
        var dto = transportadoraService.getByCnpj(cnpj);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<DetalhamentoTransportadoraDTO> updateById(@RequestBody @Valid AtualizacaoTransportadoraDTO dados) {
        var dto = transportadoraService.updateById(dados);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        transportadoraService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
