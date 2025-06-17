package com.poo.marketonic.service;

import com.poo.marketonic.model.Produto;
import java.util.List;
import java.util.Optional;

public interface ProdutoService {

    /**
     * Cria um novo produto após aplicar validações de negócio.
     * @param produto O produto a ser criado, sem ID.
     * @return O produto salvo com seu ID gerado.
     */
    Produto criar(Produto produto);

    /**
     * Retorna uma lista com todos os produtos cadastrados.
     * @return Uma lista de todos os produtos.
     */
    List<Produto> listarTodos();
    /**
     * Busca um produto específico pelo seu ID.
     * @param id O ID único do produto.
     * @return Um Optional contendo o produto encontrado, ou um Optional vazio se não houver produto com o ID fornecido.
     */
    Optional<Produto> buscarPorId(Long id);

    /**
     * Atualiza os dados de um produto já existente.
     * @param id O ID do produto a ser atualizado.
     * @param produtoAtualizado Um objeto Produto contendo os novos dados.
     * @return O produto com os dados atualizados.
     */
    Produto atualizar(Long id, Produto produtoAtualizado);

    /**
     * Deleta um produto do sistema pelo seu ID.
     * @param id O ID do produto a ser deletado.
     */
    void deletar(Long id);
}