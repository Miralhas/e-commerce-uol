# e-commerce Uol Compass

![Version](https://img.shields.io/badge/version-v0.1-blue.svg?cacheSeconds=2592000) [![Documentation](https://img.shields.io/badge/documentation-yes-brightgreen.svg)](https://github.com/YoshiDoido/ResgateRS-javamoscodar)

## Sobre o Projeto

> O projeto consiste no desenvolvimento de uma estrutura para um eCommerce, utilizando as tecnologias e conhecimentos aprendidos no curso.

## Como Usar

Para instalar e rodar esse projeto de forma local, siga os seguintes passos:

1.  Clone o repositório na sua máquina local.

    ```bash
    git clone https://github.com/Miralhas/e-commerce-uol.git
    ```

2.  Navegue até o diretório do projeto.

    ```bash
    cd e-commerce-uol
    ```

3.  Atualize as credênciais do seu banco de dados dentro da pasta `src/main/resources/application.properties`
    ```bash
    spring.application.name=e-commerce-uol
    
    spring.datasource.url=jdbc:mysql://${mysql_host}:${mysql_port}/${mysql_database}?createDatabaseIfNotExist=true&serverTimezone=UTC
    spring.datasource.username=${mysql_username}
    spring.datasource.password=${mysql_password}
    
    spring.jpa.show-sql=true
    
    spring.flyway.locations=classpath:db/migration, classpath:db/testdata
    ```

4.  Inicie o projeto.
    - Rode o método estático `main()` da classe `ECommerceUolApplication`


5.  Em seu navegador abra a documentação do projeto. (`Swagger`)
    - `http://localhost:8080/swagger-ui/index.html`
      ![image](https://github.com/Miralhas/e-commerce-uol/assets/89564433/e806e67d-e93e-41e2-89b2-071469d5734f)

## Features

**Produto:**
- [x] Permitir que os usuários criem, leiam, atualizem e excluam produtos.
- [x] Criar validações que façam sentido para os dados de entrada na criação de um produto, por exemplo: Preço do produto só pode ser positivo.
- [x] Um produto não pode ser deletado após ser incluso em uma venda, porém deve ter alguma maneira de inativar ele.
- [x] Controlar o estoque do produto de forma que ele não possa ser vendido caso o estoque seja menor do que a quantidade necessária para a venda.

**Vendas:**
- [x] Permitir que os usuários criem, leiam, atualizem e excluam vendas (Uma venda tem que ter no mínimo 1 produto para ser concluída).
- [x] Criar método para filtrar vendas por data.
  - Exemplo: `http://localhost:8080/api/orders?createdAt=2024-07-08T00:00:00Z`
  - Irá buscar todos os pedidos feitos no dia 08/07/2024.
- [x] Criar métodos de relatório de vendas, mensal e semanal.
  - Exemplo relatório mensal: `http://localhost:8080/api/orders?monthly=true`
    - Irá buscar todos os pedidos feitos no mês atual.
  - Exemplo relatório semanal: `http://localhost:8080/api/orders?weekly=true`
    - Irá buscar todos os pedidos feitos na semana atual.

**Cache**
- [x] As leituras na API devem salvar as informações no cache da aplicação, para que as próximas buscas sejam mais rápidas. Deve ser feito um bom gerenciamento do cache, por exemplo: ao inserir uma nova venda, deletar o cache de vendas para que a informação seja buscada no banco de dados diretamente e venha atualizada.
  - Obs: O cache foi implementado com Shallow Etags: [Spring Docs Shallow Etags Cache](https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-caching.html) | [Shallow ETags](https://docs.spring.io/spring-framework/reference/web/webmvc/filters.html#filters-shallow-etag)

**Tratamento de Exceções**
- [x] Todas as exceções devem ser tratadas e seguir o mesmo padrão de resposta.
  - Obs: Todas as exceptions são tratadas seguindo padrão recomendado pela equipe do Spring, o RFC 9457.
  - [Spring RFC 9457](https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-ann-rest-exceptions.html)
  - [RFC 9457](https://datatracker.ietf.org/doc/html/rfc9457)


## Modelo Entidade Relacionamento

![entidade_relacionamento](https://github.com/Miralhas/e-commerce-uol/assets/89564433/35467533-7ad2-40ff-a2f8-fd50f0f197a7)
