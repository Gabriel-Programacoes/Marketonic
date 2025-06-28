// Local: src/main/java/com/poo/marketonic/service/CategoriaServiceImpl.java
package com.poo.marketonic.service;

import com.poo.marketonic.model.Categoria;
import com.poo.marketonic.repository.CategoriaRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public Categoria criar(Categoria categoria) {
        if (categoria.getNome() == null || categoria.getNome().isBlank()) {
            throw new IllegalArgumentException("O nome da categoria não pode ser vazio.");
        }
        return categoriaRepository.save(categoria);
    }

    @Override
    public List<Categoria> listarTodas() {
        return categoriaRepository.findAll();
    }

    @Override
    public Optional<Categoria> buscarPorId(Long id) {
        return categoriaRepository.findById(id);
    }

    @Override
    public Categoria atualizar(Long id, Categoria categoriaComNovosDados) {
        Categoria categoriaExistente = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada com o ID: " + id));

        // Regra de negócio continua a mesma
        if (categoriaComNovosDados.getNome() == null || categoriaComNovosDados.getNome().isBlank()) {
            throw new IllegalArgumentException("O nome da categoria não pode ser vazio.");
        }

        categoriaExistente.setNome(categoriaComNovosDados.getNome());
        return categoriaRepository.save(categoriaExistente);
    }

    @Override
    public void deletar(Long id) {
        if (!categoriaRepository.existsById(id)) {
            throw new RuntimeException("Categoria não encontrada com o ID: " + id);
        }
        categoriaRepository.deleteById(id);
    }
}