package com.poo.marketonic.service;

import com.poo.marketonic.model.Produto;
import com.poo.marketonic.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoServiceImpl(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    public Produto criar(Produto produto) {
        if (produto.getPreco() < 0) {
            throw new IllegalArgumentException("O preço do produto não pode ser negativo.");
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
        produtoExistente.setNome(produtoComNovosDados.getNome());
        produtoExistente.setDescricao(produtoComNovosDados.getDescricao());
        produtoExistente.setPreco(produtoComNovosDados.getPreco());
        return produtoRepository.salvar(produtoExistente);
    }

    @Override
    public void deletar(Long id) {
        produtoRepository.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com o ID: " + id));
        produtoRepository.deletarPorId(id);
    }
}