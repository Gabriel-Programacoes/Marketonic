// Local: src/main/java/com/poo/marketonic/service/ProdutoService.java
package com.poo.marketonic.service;

import com.poo.marketonic.model.Produto;
import java.util.List;
import java.util.Optional;

public interface ProdutoService {

    Produto criar(Produto produto);

    List<Produto> listarTodos();

    Optional<Produto> buscarPorId(Long id);

    Produto atualizar(Long id, Produto produtoAtualizado);

    void deletar(Long id);

    // Métodos específicos que agora usarão o poder do JpaRepository
    List<Produto> buscarPorCategoriaId(Long categoriaId);

    Produto atualizarEstoque(Long id, int quantidade);

    List<Produto> listarProdutosComEstoqueBaixo();

    List<Produto> listarProdutosVencidos();

    List<Produto> listarProdutosProximosDoVencimento();
}