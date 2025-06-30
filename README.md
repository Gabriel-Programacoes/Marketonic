# Marketonic API

## 📖 Sobre o Projeto

**Marketonic** é uma API RESTful desenvolvida em Spring Boot para o gerenciamento de produtos e categorias de um supermercado. O projeto permite o controle completo de cadastro de produtos, gerenciamento de estoque, controle de validade e organização por categorias.

---

## ✨ Funcionalidades

* **Gerenciamento de Categorias**:
    * CRUD (Criar, Ler, Atualizar, Deletar) completo para categorias.
    * Listar todos os produtos pertencentes a uma categoria específica.
* **Gerenciamento de Produtos**:
    * CRUD completo para produtos, incluindo informações como preço, fornecedor e código de barras.
    * Associação de produtos a categorias existentes.
* **Controle de Estoque e Validade**:
    * Endpoint dedicado para atualização de estoque.
    * Alertas para produtos com **estoque baixo**.
    * Alertas para produtos **vencidos**.
    * Alertas para produtos **próximos do vencimento**.
* **Configuração de CORS**:
    * Configuração global de CORS para permitir requisições de frontends em outros domínios (ex: `http://localhost:3002`).

---

## 🛠️ Tecnologias Utilizadas

* **Backend**:
    * Java 21
    * Spring Boot
    * Spring Data JPA
    * Maven
* **Banco de Dados**:
    * H2 Database (em arquivo)
* **Utilitários**:
    * Lombok
    * Spring Boot DevTools

---

## 🚀 Como Executar o Projeto

### Pré-requisitos

* JDK 21 ou superior.
* Maven 3.x.
* Uma IDE de sua preferência (IntelliJ, VS Code, Eclipse).

### Passos

1.  **Clone o repositório:**
    ```bash
    git clone https://github.com/Gabriel-Programacoes/Marketonic
    ```
2.  **Navegue até a pasta do projeto:**
    ```bash
    cd marketonic
    ```
3.  **Execute o projeto usando o Maven:**
    ```bash
    mvn spring-boot:run
    ```
4.  A API estará disponível em `http://localhost:9971`.

---

## 📄 Endpoints da API

A URL base para todos os endpoints é `http://localhost:9971/api`.

### Categorias (`/api/categorias`)

| Método | Endpoint                | Descrição                                  |
| :----- | :---------------------- | :----------------------------------------- |
| `GET`  | `/`                     | Lista todas as categorias.                 |
| `GET`  | `/{id}`                 | Busca uma categoria por ID.                |
| `GET`  | `/{id}/produtos`        | Lista todos os produtos de uma categoria. |
| `POST` | `/`                     | Cria uma nova categoria.                   |
| `PUT`  | `/{id}`                 | Atualiza uma categoria existente.          |
| `DELETE`| `/{id}`                 | Deleta uma categoria.                     |

**Exemplo de JSON para `POST` / `PUT`:**
```json
{
    "nome": "Bebidas"
}
```

### Produtos (`/api/produtos`)

| Método | Endpoint                         | Descrição                                        |
| :----- | :------------------------------- | :----------------------------------------------- |
| `GET`  | `/`                              | Lista todos os produtos.                         |
| `GET`  | `/{id}`                          | Busca um produto por ID.                         |
| `POST` | `/`                              | Cria um novo produto.                            |
| `PUT`  | `/{id}`                          | Atualiza um produto existente.                   |
| `PATCH`| `/{id}/estoque`                  | Atualiza o estoque de um produto.                |
| `DELETE`| `/{id}`                          | Deleta um produto.                             |
| `GET`  | `/alertas/estoque-baixo`         | Lista produtos com estoque baixo.                |
| `GET`  | `/alertas/vencidos`              | Lista produtos vencidos.                         |
| `GET`  | `/alertas/proximo-vencimento`    | Lista produtos próximos do vencimento.           |

**Exemplo de JSON para `POST` / `PUT`:**
```json
{
    "nome": "Leite Integral UHT",
    "descricao": "Leite longa vida 1L",
    "preco": 4.50,
    "quantidadeEmEstoque": 100,
    "dataDeValidade": "2025-12-31",
    "categoria": {
        "id": 1 
    }
}
```

**Exemplo de JSON para `PATCH`:**
```json
{
    "quantidade": 150
}
```

---

## 🗄️ Acessando o Banco de Dados

O projeto utiliza o banco de dados H2, e sua console web está habilitada para fácil acesso e gerenciamento dos dados durante o desenvolvimento.

* **URL da Console H2**: `http://localhost:9971/h2-console`
* **JDBC URL**: `jdbc:h2:file:./marketonicdb`
* **Username**: `sa`
* **Password**: `password`

Basta preencher os campos na tela de login da console H2 com os dados acima e clicar em "Conectar".
