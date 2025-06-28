// Local: src/main/java/com/poo/marketonic/service/CategoriaService.java
package com.poo.marketonic.service;

import com.poo.marketonic.model.Categoria;
import java.util.List;
import java.util.Optional;

public interface CategoriaService {

    Categoria criar(Categoria categoria);

    List<Categoria> listarTodas();

    Optional<Categoria> buscarPorId(Long id);

    Categoria atualizar(Long id, Categoria categoria);

    void deletar(Long id);
}