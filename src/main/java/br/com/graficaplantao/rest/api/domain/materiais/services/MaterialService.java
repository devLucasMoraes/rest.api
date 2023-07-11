package br.com.graficaplantao.rest.api.domain.materiais.services;

import br.com.graficaplantao.rest.api.domain.categorias.Unidade;
import br.com.graficaplantao.rest.api.domain.categorias.services.CategoriaService;
import br.com.graficaplantao.rest.api.domain.itensTransacoesEntrada.ItemTransacaoEntrada;
import br.com.graficaplantao.rest.api.domain.itensTransacoesEntrada.dto.request.AtualizacaoItemTransacaoEntradaDTO;
import br.com.graficaplantao.rest.api.domain.itensTransacoesEntrada.dto.request.NovoItemTransacaoEntradaDTO;
import br.com.graficaplantao.rest.api.domain.materiais.Material;
import br.com.graficaplantao.rest.api.domain.materiais.MaterialRepository;
import br.com.graficaplantao.rest.api.domain.materiais.dto.request.AtualizacaoMaterialDTO;
import br.com.graficaplantao.rest.api.domain.materiais.dto.request.NovoMaterialDTO;
import br.com.graficaplantao.rest.api.domain.materiais.dto.response.DetalhamentoMaterialDTO;
import br.com.graficaplantao.rest.api.domain.materiais.dto.response.ListagemMateriaisDTO;
import br.com.graficaplantao.rest.api.domain.transacoesEntrada.TransacaoEntrada;
import br.com.graficaplantao.rest.api.domain.vinculosDeMateriaisComFornecedoras.services.VinculoMaterialComFornecedoraService;
import br.com.graficaplantao.rest.api.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class MaterialService {

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private VinculoMaterialComFornecedoraService vinculoComFornecedorasService;

    @Transactional
    public DetalhamentoMaterialDTO create(NovoMaterialDTO dados) {

        var categoria = categoriaService.getEntityById(dados.idCategoria());

        var material = new Material(null, dados.descricao(), dados.valorUnt(), new BigDecimal(0), categoria, new ArrayList<>());

        if (dados.fornecedorasVinculadas() != null && !dados.fornecedorasVinculadas().isEmpty()) {
            vinculoComFornecedorasService.adicionarVinculoComFornecedoras(dados.fornecedorasVinculadas(), material);
        }

        materialRepository.save(material);

        return new  DetalhamentoMaterialDTO(material);
    }

    public Page<ListagemMateriaisDTO> getAll(Pageable pageable, String descricao) {
        return materialRepository.findByDescricaoContainingIgnoreCase(descricao, pageable).map(ListagemMateriaisDTO::new);
    }

    public DetalhamentoMaterialDTO getById(Long id) {
        var material = materialRepository.getReferenceById(id);
        return new DetalhamentoMaterialDTO(material);
    }

    public DetalhamentoMaterialDTO getByCodProd(String codProd) {
        var materialId = vinculoComFornecedorasService.getMaterialId(codProd);
        var material = materialRepository.getReferenceById(materialId);
        return new DetalhamentoMaterialDTO(material);
    }

    @Transactional
    public DetalhamentoMaterialDTO updateById(Long id,AtualizacaoMaterialDTO dados) {
        var material = materialRepository.getReferenceById(id);

        var categoria = categoriaService.getEntityById((dados.idCategoria()));

        if (dados.fornecedorasVinculadas() == null || dados.fornecedorasVinculadas().isEmpty())  {
            material.getFornecedorasVinculadas().clear();
        }

        if (dados.fornecedorasVinculadas() != null) {
            vinculoComFornecedorasService.atualizarVinculoComFornecedoras(dados.fornecedorasVinculadas(), material);
        }

        material.update(
                dados,
                categoria
        );

        materialRepository.save(material);

        return new DetalhamentoMaterialDTO(material);
    }

    @Transactional
    public void deleteById(Long id) {
        materialRepository.deleteById(id);
    }

    public Material getEntityById(Long id) {
        if (!materialRepository.existsById(id)) {
            throw new ValidacaoException("Id do material informado não existe");
        }
        return materialRepository.getReferenceById(id);
    }

    @Transactional
    public void adcionarAoEstoque(TransacaoEntrada transacaoEntrada) {
        var fornecedora = transacaoEntrada.getFornecedora();

        for (ItemTransacaoEntrada item : transacaoEntrada.getItens()) {
            Material material = item.getMaterial();
            Unidade undPadrao = material.getCategoria().getUndPadrao();
            Unidade undCompra = item.getUndCom();
            BigDecimal QtdEmEstoque = material.getQtdEmEstoque();
            BigDecimal QtdeComprada = item.getQuantCom();
            var vinculo = material.getFornecedorasVinculadas();


            BigDecimal novaQuantidade = QtdEmEstoque.add(QtdeComprada);
            material.setQtdEmEstoque(novaQuantidade);
            materialRepository.save(material);
        }
    }

    @Transactional
    public void deletarDoEstoque(ItemTransacaoEntrada item) {
            Material material = item.getMaterial();
            BigDecimal QtdEmEstoque = material.getQtdEmEstoque();
            BigDecimal novaQuantidade = QtdEmEstoque.subtract(item.getQuantCom());
            material.setQtdEmEstoque(novaQuantidade);
            materialRepository.save(material);
    }

    @Transactional
    public void atualizarEstoque(TransacaoEntrada transacaoEntrada, List<AtualizacaoItemTransacaoEntradaDTO> listaAtualizadaDTO) {
        for (ItemTransacaoEntrada item : transacaoEntrada.getItens()) {
            deletarDoEstoque(item);
        }
        for (var itemAtualizado : listaAtualizadaDTO) {
            Material material = getEntityById(itemAtualizado.idMaterial());
            BigDecimal QtdEmEstoque = material.getQtdEmEstoque();
            BigDecimal novaQuantidade = QtdEmEstoque.add(itemAtualizado.quantCom());
            material.setQtdEmEstoque(novaQuantidade);
            materialRepository.save(material);
        }

    }

    private void validarUnidadeMedida(NovoItemTransacaoEntradaDTO item, Material material) {
        String undPadrao = material.getCategoria().getUndPadrao().toString();
        String undCom = item.undCom().toString();

        if (!undPadrao.equals(undCom)) {
            throw new ValidacaoException("A unidade de compra não possui conversão para a unidade padrão");
        }
    }

}
