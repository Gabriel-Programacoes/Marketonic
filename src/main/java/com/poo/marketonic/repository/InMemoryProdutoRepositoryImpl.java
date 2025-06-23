package com.poo.marketonic.repository;

import com.poo.marketonic.model.Produto;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class InMemoryProdutoRepositoryImpl implements ProdutoRepository {

    private final Map<Long, Produto> produtos = new HashMap<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    @Override
    public Produto salvar(Produto produto) {
        if (produto.getId() == null) {
            produto.setId(idCounter.getAndIncrement());
        }
        produtos.put(produto.getId(), produto);
        return produto;
    }

    @Override
    public List<Produto> listarTodos() {
        return new ArrayList<>(produtos.values());

    }

    @Override
    public List<Produto> buscarPorCategoriaId(Long categoriaId) {
        // Usa Stream API para filtrar a lista de produtos
        return produtos.values().stream()
                .filter(produto -> categoriaId.equals(produto.getCategoriaId()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Produto> buscarPorId(Long id) {
        return Optional.ofNullable(produtos.get((id)));
    }

    @Override
    public void deletarPorId(Long id) {
        produtos.remove(id);
    }
}