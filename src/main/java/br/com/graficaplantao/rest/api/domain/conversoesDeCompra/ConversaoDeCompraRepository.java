package br.com.graficaplantao.rest.api.domain.conversoesDeCompra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConversaoDeCompraRepository extends JpaRepository<ConversaoDeCompra, Long> {
}
