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

## Modelo Entidade Relacionamento

![entidade_relacionamento](https://github.com/Miralhas/e-commerce-uol/assets/89564433/35467533-7ad2-40ff-a2f8-fd50f0f197a7)
