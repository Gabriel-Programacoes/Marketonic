package com.poo.marketonic.repository;

import com.poo.marketonic.model.Produto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;


public interface ProdutoRepository {
    Produto salvar(Produto Produto);
    List<Produto> listarTodos();
    Optional<Produto> buscarPorId(Long id);
    void deletarPorId(Long id);
}