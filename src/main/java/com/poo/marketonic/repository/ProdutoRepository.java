package com.poo.marketonic.repository;

import com.poo.marketonic.model.Produto;

import java.util.List;

import java.util.Optional;


public interface ProdutoRepository {
    Produto salvar(Produto Produto);

    List<Produto> listarTodos();

    Optional<Produto> buscarPorId(Long id);

    void deletarPorId(Long id);
}