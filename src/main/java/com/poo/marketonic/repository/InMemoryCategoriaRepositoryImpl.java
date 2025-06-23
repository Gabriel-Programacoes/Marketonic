package com.poo.marketonic.repository;

import com.poo.marketonic.model.Categoria;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryCategoriaRepositoryImpl implements CategoriaRepository {

    private final Map<Long, Categoria> categorias = new HashMap<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    @Override
    public Categoria salvar(Categoria categoria) {
        if (categoria.getId() == null) {
            categoria.setId(idCounter.getAndIncrement());
        }
        categorias.put(categoria.getId(), categoria);
        return categoria;
    }

    @Override
    public List<Categoria> listarTodas() {
        return new ArrayList<>(categorias.values());
    }

    @Override
    public Optional<Categoria> buscarPorId(Long id) {
        return Optional.ofNullable(categorias.get(id));
    }

    @Override
    public void deletarPorId(Long id) {
        categorias.remove(id);
    }
}