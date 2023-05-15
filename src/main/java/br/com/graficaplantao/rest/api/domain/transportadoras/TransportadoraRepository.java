package br.com.graficaplantao.rest.api.domain.transportadoras;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransportadoraRepository extends JpaRepository<Transportadora, Long> {
    Page<Transportadora> findByNomeFantasiaContainingIgnoreCase(String nomeFantasia, Pageable pageable);

    Transportadora getReferenceByCnpj(String cnpj);
}
