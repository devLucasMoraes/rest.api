package br.com.graficaplantao.rest.api.domain.transportadoras.services;

import br.com.graficaplantao.rest.api.domain.transportadoras.Transportadora;
import br.com.graficaplantao.rest.api.domain.transportadoras.TransportadoraRepository;
import br.com.graficaplantao.rest.api.domain.transportadoras.dto.request.AtualizacaoTransportadoraDTO;
import br.com.graficaplantao.rest.api.domain.transportadoras.dto.request.NovaTransportadoraDTO;
import br.com.graficaplantao.rest.api.domain.transportadoras.dto.response.DetalhamentoTransportadoraDTO;
import br.com.graficaplantao.rest.api.domain.transportadoras.dto.response.ListagemTransportadoraDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TrasportadoraService {

    @Autowired
    private TransportadoraRepository transportadoraRepository;
    public DetalhamentoTransportadoraDTO crate(NovaTransportadoraDTO dados) {
        var transportadora = new Transportadora(dados);
        transportadoraRepository.save(transportadora);
        return new DetalhamentoTransportadoraDTO(transportadora);
    }

    public Page<ListagemTransportadoraDTO> getAll(Pageable pageable) {
        return transportadoraRepository.findAll(pageable).map(transportadora -> new ListagemTransportadoraDTO(transportadora));
    }

    public DetalhamentoTransportadoraDTO getById(Long id) {
        var transportadora = transportadoraRepository.getReferenceById(id);
        return new DetalhamentoTransportadoraDTO(transportadora);
    }

    public DetalhamentoTransportadoraDTO updateById(AtualizacaoTransportadoraDTO dados) {
        var transportadora = transportadoraRepository.getReferenceById(dados.id());
        transportadora.update(dados);
        return new DetalhamentoTransportadoraDTO(transportadora);
    }

    public void deleteById(Long id) {
        transportadoraRepository.deleteById(id);
    }
}
