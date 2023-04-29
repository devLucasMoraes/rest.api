package br.com.graficaplantao.rest.api.domain.fornecedoras;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FornecedoraRepository extends JpaRepository<Fornecedora, Long> {
}
