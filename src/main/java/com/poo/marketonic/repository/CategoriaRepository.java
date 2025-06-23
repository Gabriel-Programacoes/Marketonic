package com.poo.marketonic.repository;

import com.poo.marketonic.model.Categoria;
import java.util.List;
import java.util.Optional;

public interface CategoriaRepository {

    Categoria salvar(Categoria categoria);

    List<Categoria> listarTodas();

    Optional<Categoria> buscarPorId(Long id);

    void deletarPorId(Long id);
}