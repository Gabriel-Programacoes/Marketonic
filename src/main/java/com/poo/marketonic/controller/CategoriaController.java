package com.poo.marketonic.controller;

import com.poo.marketonic.model.Categoria;
import com.poo.marketonic.model.Produto;

import com.poo.marketonic.service.CategoriaService;
import com.poo.marketonic.service.ProdutoService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/categorias")
@CrossOrigin(origins = "*")
public class CategoriaController {

    private final CategoriaService categoriaService;
    private final ProdutoService produtoService;


    public CategoriaController(CategoriaService categoriaService, ProdutoService produtoService) {
        this.categoriaService = categoriaService;
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<Categoria> criarCategoria(@RequestBody Categoria categoria) {
        Categoria novaCategoria = categoriaService.criar(categoria);
        return new ResponseEntity<>(novaCategoria, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> listarTodasCategorias() {
        return ResponseEntity.ok(categoriaService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscarCategoriaPorId(@PathVariable Long id) {
        return categoriaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/produtos")
    public ResponseEntity<List<Produto>> listarProdutosPorCategoria(@PathVariable Long id) {
        categoriaService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada com o ID: " + id));

        List<Produto> produtos = produtoService.buscarPorCategoriaId(id);
        return ResponseEntity.ok(produtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> atualizarCategoria(@PathVariable Long id, @RequestBody Categoria categoria) {
        Categoria categoriaAtualizada = categoriaService.atualizar(id, categoria);
        return ResponseEntity.ok(categoriaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCategoria(@PathVariable Long id) {
        categoriaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}