package br.com.graficaplantao.rest.api.domain.vinculosDeMateriaisComFornecedoras;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VinculoMaterialComFornecedoraRepository extends JpaRepository<VinculoMaterialComFornecedora, Long> {
    VinculoMaterialComFornecedora getReferenceByCodProd(String codProd);
}
