package com.poo.marketonic.controller;

import com.poo.marketonic.model.Produto;
import com.poo.marketonic.service.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/produtos")
@CrossOrigin(origins = "*")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<Produto> criarProduto (@RequestBody Produto produto){
        Produto novoProduto = produtoService.criar(produto);
        return new ResponseEntity<> (novoProduto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Produto>> listarTodosProdutos() {
        return ResponseEntity.ok(produtoService.listarTodos());
    }

    //Endpoint: Listar produtos com estoque baixo
    @GetMapping("/alertas/estoque-baixo")
    public ResponseEntity<List<Produto>> getProdutosComEstoqueBaixo() {
        List<Produto> produtos = produtoService.listarProdutosComEstoqueBaixo();
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/alertas/vencidos")
    public ResponseEntity<List<Produto>> getProdutosVencidos() {
        List<Produto> produtos = produtoService.listarProdutosVencidos();
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/alertas/proximo-vencimento")
    public ResponseEntity<List<Produto>> getProdutosProximosDoVencimento() {
        List<Produto> produtos = produtoService.listarProdutosProximosDoVencimento();
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarProdutoPorId (@PathVariable Long id) {
        return produtoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse (ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizarProduto(@PathVariable Long id, @RequestBody Produto produtoComNovosDados) {
        Produto produtoAtualizado = produtoService.atualizar(id, produtoComNovosDados);
        return ResponseEntity.ok(produtoAtualizado);
    }

    //Endpoint: Atualizar estoque de um produto (entrada/saída)
    @PatchMapping("/{id}/estoque")
    public ResponseEntity<Produto> atualizarEstoque(@PathVariable Long id, @RequestBody Map<String, Integer> requestBody) {
        Integer quantidade = requestBody.get("quantidade");
        if (quantidade == null) {
            // Retorna um erro se o corpo do JSON não contiver a chave "quantidade"
            return ResponseEntity.badRequest().build();
        }
        Produto produtoAtualizado = produtoService.atualizarEstoque(id, quantidade);
        return ResponseEntity.ok(produtoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
        produtoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}