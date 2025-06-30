package com.poo.marketonic.service;

import com.poo.marketonic.model.Categoria;
import com.poo.marketonic.model.Produto;
import com.poo.marketonic.repository.CategoriaRepository;
import com.poo.marketonic.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoServiceImpl implements ProdutoService {
    
    private static final int LIMITE_ESTOQUE_BAIXO = 10;
    private static final int DIAS_PARA_VENCIMENTO_PROXIMO = 7;

    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;

    public ProdutoServiceImpl(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public Produto criar(Produto produto) {
        // Validação de negócio
        if (produto.getPreco() < 0) {
            throw new IllegalArgumentException("O preço do produto não pode ser negativo.");
        }

        // 1. Verifica se a informação da categoria foi enviada
        if (produto.getCategoria() == null || produto.getCategoria().getId() == null) {
            throw new IllegalArgumentException("A categoria do produto é obrigatória e precisa de um ID.");
        }

        // 2. Busca a categoria REAL e GERENCIADA pelo banco de dados usando o ID
        Categoria categoriaGerenciada = categoriaRepository.findById(produto.getCategoria().getId())
                .orElseThrow(() -> new RuntimeException("Categoria com ID " + produto.getCategoria().getId() + " não encontrada."));

        // 3. Atribui a categoria REAL ao produto que será salvo
        produto.setCategoria(categoriaGerenciada);

        // 4. Salva o produto com a referência de categoria correta e completa
        return produtoRepository.save(produto); // JPA usa save() para criar e atualizar
    }

    @Override
    public List<Produto> listarTodos() {
        return produtoRepository.findAll(); // JpaRepository usa findAll()
    }

    @Override
    public Optional<Produto> buscarPorId(Long id) {
        return produtoRepository.findById(id); // JpaRepository usa findById()
    }

    @Override
    public Produto atualizar(Long id, Produto produtoComNovosDados) {
        // Busca o produto existente no banco
        Produto produtoExistente = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com o ID: " + id));

        // Valida a nova categoria
        validarCategoria(produtoComNovosDados.getCategoria());


        // Atualiza os campos do produto existente
        produtoExistente.setNome(produtoComNovosDados.getNome());
        produtoExistente.setDescricao(produtoComNovosDados.getDescricao());
        produtoExistente.setPreco(produtoComNovosDados.getPreco());
        produtoExistente.setQuantidadeEmEstoque(produtoComNovosDados.getQuantidadeEmEstoque());
        produtoExistente.setDataDeValidade(produtoComNovosDados.getDataDeValidade());
        produtoExistente.setCategoria(produtoComNovosDados.getCategoria());

        // Salva o produto atualizado
        return produtoRepository.save(produtoExistente);
    }

    @Override
    public void deletar(Long id) {
        // Verifica se o produto existe antes de deletar
        if (!produtoRepository.existsById(id)) { // JpaRepository nos dá o existsById()
            throw new RuntimeException("Produto não encontrado com o ID: " + id);
        }
        produtoRepository.deleteById(id); // JpaRepository usa deleteById()
    }

    @Override
    public List<Produto> buscarPorCategoriaId(Long categoriaId) {
        // Chama o método que criamos na interface do repositório
        return produtoRepository.findByCategoriaId(categoriaId);
    }

    @Override
    public Produto atualizarEstoque(Long id, int quantidade) {
        if (quantidade < 0) {
            throw new IllegalArgumentException("A quantidade em estoque não pode ser negativa.");
        }
        Produto produtoExistente = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com o ID: " + id));

        produtoExistente.setQuantidadeEmEstoque(quantidade);
        return produtoRepository.save(produtoExistente);
    }

    @Override
    public List<Produto> listarProdutosComEstoqueBaixo() {
        // Chama o método do repositório, que já sabe o que fazer
        return produtoRepository.findByQuantidadeEmEstoqueLessThan(LIMITE_ESTOQUE_BAIXO);
    }

    @Override
    public List<Produto> listarProdutosVencidos() {
        // A lógica de negócio (o que é "hoje") fica no serviço
        LocalDate hoje = LocalDate.now();
        return produtoRepository.findByDataDeValidadeBefore(hoje);
    }

    @Override
    public List<Produto> listarProdutosProximosDoVencimento() {
        // A lógica de negócio (o período de "proximidade") fica no serviço
        LocalDate hoje = LocalDate.now();
        LocalDate dataLimite = hoje.plusDays(DIAS_PARA_VENCIMENTO_PROXIMO);
        return produtoRepository.findByDataDeValidadeBetween(hoje, dataLimite);
    }


    private void validarCategoria(Categoria categoria) {
        // Validação de negócio para a associação com Categoria
        if (categoria != null && categoria.getId() != null) {
            categoriaRepository.findById(categoria.getId())
                    .orElseThrow(() -> new RuntimeException("Categoria não encontrada com o ID: " + categoria.getId()));
        } else if (categoria != null && categoria.getId() == null) {

            throw new IllegalArgumentException("A categoria associada precisa ter um ID válido.");
        }
    }
}