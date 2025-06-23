package com.poo.marketonic.service;

import com.poo.marketonic.model.Produto;
import java.util.List;
import java.util.Optional;

public interface ProdutoService {

    /**
     * Cria um novo produto após aplicar validações de negócio.
     * Parâmetro = produto. O produto a ser criado, sem ID.
     * Retorna o produto salvo com seu ID gerado.
     */
    Produto criar(Produto produto);

    /**
     * Retorna uma lista com todos os produtos cadastrados.
     */
    List<Produto> listarTodos();

    /**
     * Busca um produto específico pelo seu ID.
     * Parâmetro = id. O ID único do produto.
     * Retorna um Optional contendo o produto encontrado, ou um Optional vazio se não houver produto com o ID fornecido.
     */
    Optional<Produto> buscarPorId(Long id);

    /**
     * Atualiza os dados de um produto já existente.
     * Parâmetro = id. O ID do produto a ser atualizado.
     * Parâmetro = produtoAtualizado Um objeto Produto contendo os novos dados.
     * Retorna o produto com os dados atualizados.
     */
    Produto atualizar(Long id, Produto produtoAtualizado);

    List<Produto> buscarPorCategoriaId(Long categoriaId);

    /**
     * Deleta um produto do sistema pelo seu ID.
     * Parâmetro = id. O ID do produto a ser deletado.
     */
    void deletar(Long id);
}