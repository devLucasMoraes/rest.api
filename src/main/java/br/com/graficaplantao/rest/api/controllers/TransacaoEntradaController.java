package br.com.graficaplantao.rest.api.controllers;

import br.com.graficaplantao.rest.api.domain.transacoesEntrada.dto.request.AtualizacaoTransacaoEntradaDTO;
import br.com.graficaplantao.rest.api.domain.transacoesEntrada.dto.request.NovaTransacaoEntradaDTO;
import br.com.graficaplantao.rest.api.domain.transacoesEntrada.dto.response.DetalhamentoTransacaoEntradaDTO;
import br.com.graficaplantao.rest.api.domain.transacoesEntrada.dto.response.ListagemTransacaoEntradaDTO;
import br.com.graficaplantao.rest.api.domain.transacoesEntrada.services.TransacaoEntradaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/transacoes_entrada")
public class TransacaoEntradaController {

    @Autowired
    private TransacaoEntradaService transacaoEntradaService;

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhamentoTransacaoEntradaDTO> create(@RequestBody @Valid NovaTransacaoEntradaDTO dados, UriComponentsBuilder componentsBuilder) {
        var dto = transacaoEntradaService.create(dados);
        var uri = componentsBuilder.path("/transacoes_entrada/{id}").buildAndExpand(dto.id()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping
    public ResponseEntity<Page<ListagemTransacaoEntradaDTO>> getAll(Pageable pageable) {
        var page = transacaoEntradaService.getAll(pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhamentoTransacaoEntradaDTO> getById(@PathVariable Long id) {
        var dto = transacaoEntradaService.getById(id);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    @Transactional
    public  ResponseEntity<DetalhamentoTransacaoEntradaDTO> updateById(@RequestBody @Valid AtualizacaoTransacaoEntradaDTO dados) {
        var dto = transacaoEntradaService.updateById(dados);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        transacaoEntradaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
