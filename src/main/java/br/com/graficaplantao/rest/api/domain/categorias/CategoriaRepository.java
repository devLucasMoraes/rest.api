package br.com.graficaplantao.rest.api.domain.categorias;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    Page<Categoria> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
}
