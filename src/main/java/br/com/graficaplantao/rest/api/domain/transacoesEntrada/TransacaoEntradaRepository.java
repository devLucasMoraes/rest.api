package br.com.graficaplantao.rest.api.domain.transacoesEntrada;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransacaoEntradaRepository extends JpaRepository<TransacaoEntrada, Long> {
}
