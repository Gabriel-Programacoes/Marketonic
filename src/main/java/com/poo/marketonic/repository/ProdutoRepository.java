package com.poo.marketonic.repository;

import com.poo.marketonic.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    // Mágica do Spring Data JPA: basta declarar o método com o nome correto
    // e ele cria a consulta SQL para você!

    List<Produto> findByCategoriaId(Long categoriaId);

    List<Produto> findByQuantidadeEmEstoqueLessThan(int limite);

    List<Produto> findByDataDeValidadeBefore(LocalDate data);

    List<Produto> findByDataDeValidadeBetween(LocalDate dataInicio, LocalDate dataFim);
}