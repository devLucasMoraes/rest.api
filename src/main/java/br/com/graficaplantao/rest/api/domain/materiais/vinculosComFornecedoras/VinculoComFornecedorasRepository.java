package br.com.graficaplantao.rest.api.domain.materiais.vinculosComFornecedoras;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VinculoComFornecedorasRepository extends JpaRepository<VinculoComFornecedoras, Long> {
    VinculoComFornecedoras getReferenceByCodProd(String codProd);
}
