package br.com.graficaplantao.rest.api.domain.fornecedoras;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FornecedoraRepository extends JpaRepository<Fornecedora, Long> {

    Page<Fornecedora> findByNomeFantasiaContainingIgnoreCase(String nomeFantasia, Pageable pageable);

    Fornecedora getReferenceByCnpj(String cnpj);
}
