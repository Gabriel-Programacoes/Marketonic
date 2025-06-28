package com.poo.marketonic.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity // Diz ao JPA que esta classe é uma tabela no banco
@Data
public class Categoria {

    @Id // Marca este campo como a chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Diz ao banco para gerar o valor do ID automaticamente
    private Long id;

    private String nome;
}
