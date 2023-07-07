package br.com.graficaplantao.rest.api.domain.itensTransacoesEntrada;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemTransacaoEntradaRepository extends JpaRepository<ItemTransacaoEntrada, Long> {
}
