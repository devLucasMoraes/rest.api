package br.com.graficaplantao.rest.api.domain.materiais;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {
    Page<Material> findByDescricaoContainingIgnoreCase(String descricao, Pageable pageable);
}
