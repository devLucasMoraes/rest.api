package br.com.graficaplantao.rest.api.domain.transportadoras.services;

import br.com.graficaplantao.rest.api.domain.transportadoras.Transportadora;
import br.com.graficaplantao.rest.api.domain.transportadoras.TransportadoraRepository;
import br.com.graficaplantao.rest.api.domain.transportadoras.dto.request.AtualizacaoTransportadoraDTO;
import br.com.graficaplantao.rest.api.domain.transportadoras.dto.request.NovaTransportadoraDTO;
import br.com.graficaplantao.rest.api.domain.transportadoras.dto.response.DetalhamentoTransportadoraDTO;
import br.com.graficaplantao.rest.api.domain.transportadoras.dto.response.ListagemTransportadoraDTO;
import br.com.graficaplantao.rest.api.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransportadoraService {

    @Autowired
    private TransportadoraRepository transportadoraRepository;

    @Transactional
    public DetalhamentoTransportadoraDTO create(NovaTransportadoraDTO dados) {
        var transportadora = new Transportadora(dados);
        transportadoraRepository.save(transportadora);
        return new DetalhamentoTransportadoraDTO(transportadora);
    }

    public Page<ListagemTransportadoraDTO> getAll(Pageable pageable, String nomeFantasia) {
        return transportadoraRepository.findByNomeFantasiaContainingIgnoreCase(nomeFantasia, pageable).map(transportadora -> new ListagemTransportadoraDTO(transportadora));
    }

    public DetalhamentoTransportadoraDTO getById(Long id) {
        var transportadora = transportadoraRepository.getReferenceById(id);
        return new DetalhamentoTransportadoraDTO(transportadora);
    }

    public DetalhamentoTransportadoraDTO getByCnpj(String cnpj) {
        var transportadora = transportadoraRepository.getReferenceByCnpj(cnpj);
        if (transportadora == null) {
            throw new ValidacaoException("CNPJ da transportadora informada não existe");
        }
        return new DetalhamentoTransportadoraDTO(transportadora);
    }

    @Transactional
    public DetalhamentoTransportadoraDTO updateById(Long id, AtualizacaoTransportadoraDTO dados) {
        var transportadora = transportadoraRepository.getReferenceById(id);
        transportadora.update(dados);
        return new DetalhamentoTransportadoraDTO(transportadora);
    }

    @Transactional
    public void deleteById(Long id) {
        transportadoraRepository.deleteById(id);
    }

    public Transportadora getEntityById(Long id) {
        if (!transportadoraRepository.existsById(id)) {
            throw new ValidacaoException("Id da transportadora informado não existe");
        }
        return transportadoraRepository.getReferenceById(id);
    }
}
