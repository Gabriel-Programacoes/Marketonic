package com.poo.marketonic.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;
    private double preco;
    private int quantidadeEmEstoque;
    private LocalDate dataDeValidade;

    @ManyToOne // Define a relação: Muitos Produtos para Uma Categoria
    @JoinColumn(name = "categoria_id") // Nome da coluna da chave estrangeira no banco
    private Categoria categoria;
}