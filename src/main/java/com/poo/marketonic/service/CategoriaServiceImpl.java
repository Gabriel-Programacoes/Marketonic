package com.poo.marketonic.service;

import com.poo.marketonic.model.Categoria;
import com.poo.marketonic.repository.CategoriaRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;

    // Injeção de Dependência via construtor
    public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public Categoria criar(Categoria categoria) {
        // Exemplo de regra de negócio: nome da categoria não pode ser vazio
        if (categoria.getNome() == null || categoria.getNome().isBlank()) {
            throw new IllegalArgumentException("O nome da categoria não pode ser vazio.");
        }
        return categoriaRepository.salvar(categoria);
    }

    @Override
    public List<Categoria> listarTodas() {
        return categoriaRepository.listarTodas();
    }

    @Override
    public Optional<Categoria> buscarPorId(Long id) {
        return categoriaRepository.buscarPorId(id);
    }

    @Override
    public Categoria atualizar(Long id, Categoria categoriaComNovosDados) {
        Categoria categoriaExistente = categoriaRepository.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada com o ID: " + id));

        // Regra de negócio para a atualização
        if (categoriaComNovosDados.getNome() == null || categoriaComNovosDados.getNome().isBlank()) {
            throw new IllegalArgumentException("O nome da categoria não pode ser vazio.");
        }

        categoriaExistente.setNome(categoriaComNovosDados.getNome());
        return categoriaRepository.salvar(categoriaExistente);
    }

    @Override
    public void deletar(Long id) {
        // Verifica se a categoria existe antes de deletar
        categoriaRepository.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada com o ID: " + id));
        categoriaRepository.deletarPorId(id);
    }
}