# Desafio para a Vivo

Para correr o projeto, corra o seguinte comando:
```shell script
mvn clean install
docker-compose up --build
```
Para testar a aplicação:

http://localhost:8080/q/swagger-ui

Ou mesmo podem baixar a especificação Openapi para usar Postman, por exemplo:

http://localhost:8080/q/openapi


## Sobre o projeto

Este projeto foi desenvovido em Quarkus, utilizando-se de uma arquitetura EBC (Entity, Boundary, Control),
na qual a camada Boundary é responsável pelas requests de e reponses para o cliente.

Ao receber um DTO (data transfer object), a classe de Resource vai processar a informação necessária,
fazendo um mapeamento do dto com a entidade (utilizei Mapstruct para isso).

Não foi contemplado métodos de CRUD (existe apenas o GET que retorna um objeto com porcentagem, conforme especificação),
mas o projeto está aberto a ampliação e versionamento (atualmente, V1, enviado via Header).

Decidiu-se por não haver Repository, pelo fato de a biblioteca Panache + Hibernate já trazer de forma muito facilidade a possibilidade de acessar a BD com métodos estáticos.

Para versionamento da BD, inclui-se o Liquibase, com o script inicial para criação da tabela "Chocolate", bem como para inclusão de dados nela.

Nos testes, optou-se por JUnit5, RestAssured, TestContainers (BD Postgres) e DBRider (para "mock" de dados da BD).

Por fim, foi incluído também:
- BD Postgres
- Opentracing/Jaeger (http://localhost:16686/)
- Metrics com Prometheus (http://localhost:9090/)