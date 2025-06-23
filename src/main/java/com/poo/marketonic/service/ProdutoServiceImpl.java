package com.poo.marketonic.service;

import com.poo.marketonic.model.Produto;
import com.poo.marketonic.repository.ProdutoRepository;
import com.poo.marketonic.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;


    public ProdutoServiceImpl(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public Produto criar(Produto produto) {
        if (produto.getPreco() < 0) {
            throw new IllegalArgumentException("O preço do produto não pode ser negativo.");
        }

        if (produto.getCategoriaId() != null) {
            categoriaRepository.buscarPorId(produto.getCategoriaId())
                    .orElseThrow(() -> new RuntimeException("Categoria não encontrada com o ID: " + produto.getCategoriaId()));
        }

        return produtoRepository.salvar(produto);
    }

    @Override
    public List<Produto> listarTodos() {
        return produtoRepository.listarTodos();
    }

    @Override
    public Optional<Produto> buscarPorId(Long id) {
        return produtoRepository.buscarPorId(id);
    }

    @Override
    public Produto atualizar(Long id, Produto produtoComNovosDados) {
        Produto produtoExistente = produtoRepository.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com o ID: " + id));
        if (produtoComNovosDados.getPreco() < 0) {
            throw new IllegalArgumentException("O preço do produto não pode ser negativo.");
        }

        if (produtoComNovosDados.getCategoriaId() != null) {
            categoriaRepository.buscarPorId(produtoComNovosDados.getCategoriaId())
                    .orElseThrow(() -> new RuntimeException("Categoria não encontrada com o ID: " + produtoComNovosDados.getCategoriaId()));
        }

        produtoExistente.setNome(produtoComNovosDados.getNome());
        produtoExistente.setDescricao(produtoComNovosDados.getDescricao());
        produtoExistente.setPreco(produtoComNovosDados.getPreco());
        produtoExistente.setCategoriaId(produtoComNovosDados.getCategoriaId());
        return produtoRepository.salvar(produtoExistente);
    }

    @Override
    public List<Produto> buscarPorCategoriaId(Long categoriaId) {
        return produtoRepository.buscarPorCategoriaId(categoriaId);
    }

    @Override
    public void deletar(Long id) {
        produtoRepository.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com o ID: " + id));
        produtoRepository.deletarPorId(id);
    }
}