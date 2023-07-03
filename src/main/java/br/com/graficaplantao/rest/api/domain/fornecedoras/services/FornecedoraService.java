package br.com.graficaplantao.rest.api.domain.fornecedoras.services;

import br.com.graficaplantao.rest.api.domain.fornecedoras.Fornecedora;
import br.com.graficaplantao.rest.api.domain.fornecedoras.FornecedoraRepository;
import br.com.graficaplantao.rest.api.domain.fornecedoras.dto.request.AtualizacaoFornecedoraDTO;
import br.com.graficaplantao.rest.api.domain.fornecedoras.dto.request.NovaFornecedoraDTO;
import br.com.graficaplantao.rest.api.domain.fornecedoras.dto.response.DetalhamentoFornecedoraDTO;
import br.com.graficaplantao.rest.api.domain.fornecedoras.dto.response.ListagemFornecedoraDTO;
import br.com.graficaplantao.rest.api.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FornecedoraService {

    @Autowired
    private FornecedoraRepository fornecedoraRepository;

    @Transactional
    public DetalhamentoFornecedoraDTO create(NovaFornecedoraDTO dados) {
        var fornecedora = new Fornecedora(dados);
        fornecedoraRepository.save(fornecedora);
        return new DetalhamentoFornecedoraDTO(fornecedora);
    }

    public Page<ListagemFornecedoraDTO> getAll(Pageable pageable, String nomeFantasia) {
        return fornecedoraRepository.findByNomeFantasiaContainingIgnoreCase(nomeFantasia, pageable).map(ListagemFornecedoraDTO::new);
    }

    public DetalhamentoFornecedoraDTO getById(Long id) {
        var fornecedora = fornecedoraRepository.getReferenceById(id);
        return new DetalhamentoFornecedoraDTO(fornecedora);
    }

    public DetalhamentoFornecedoraDTO getByCnpj(String cnpj) {
        var fornecedora = fornecedoraRepository.getReferenceByCnpj(cnpj);
        if (fornecedora == null) {
            throw new ValidacaoException("CNPJ da fornecedora informada não existe");
        }
        return new DetalhamentoFornecedoraDTO(fornecedora);
    }

    @Transactional
    public DetalhamentoFornecedoraDTO updateById(Long id, AtualizacaoFornecedoraDTO dados) {
        var fornecedora = fornecedoraRepository.getReferenceById(id);
        fornecedora.update(dados);
        return new DetalhamentoFornecedoraDTO(fornecedora);
    }

    @Transactional
    public void deleteById(Long id) {
        fornecedoraRepository.deleteById(id);
    }

    public Fornecedora getEntityById(Long id) {
        if (!fornecedoraRepository.existsById(id)) {
            throw new ValidacaoException("Id da fornecedora informada não existe");
        }
        return fornecedoraRepository.getReferenceById(id);
    }


}
