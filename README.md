#OneLibrary — sistema de gerenciamento de livros
<img width="1920" height="1012" alt="image" src="https://github.com/user-attachments/assets/9e960356-4aac-41fb-a7b1-411420af4aa3" />

Projeto de trabalho de faculdade: um sistema simples de biblioteca que expõe APIs REST e serve uma página estática que consome essas APIs.

Principais tecnologias
- Java 
- Spring Boot 3 (Spring Web, Spring Data JPA)
- Hibernate ORM
- MariaDB (via Docker / docker-compose)
- Maven (com wrapper `./mvnw`)

Visão geral do projeto
----------------------
Este repositório contém uma aplicação backend em Spring Boot que realiza operações CRUD sobre entidades típicas de uma biblioteca: autores, editoras, obras (livros, periódicos), usuários (estudantes, professores, funcionários) e empréstimos.

A aplicação também inclui um `BulkDataLoader` (executado com a flag `--app.seed.bulk=true`) que popula o banco com dados realistas — incluindo uma lista de editoras/autor nacionais e títulos — para facilitar demonstrações e testes.

Rodando localmente
------------------
Recomendo usar Docker para subir uma instância MariaDB que a aplicação irá usar por padrão (configurada no `docker-compose.yml`).

1) Subir o banco via docker-compose:

```fish
docker compose up -d mariadb
```

O `docker-compose.yml` já define:
- imagem: `mariadb:10.11`
- banco: `librarydb`
- usuário: `libraryuser` / senha: `librarypass`

2) Rodar a aplicação com seed (gera dados de exemplo):

```fish
./mvnw -Dspring-boot.run.arguments="--app.seed.bulk=true" spring-boot:run
```

Se preferir rodar em memória (H2) para testes rápidos, passe parâmetros adicionais de propriedade:

```fish
./mvnw -Dspring-boot.run.arguments="--app.seed.bulk=true --spring.datasource.url=jdbc:h2:mem:librarydb --spring.datasource.driverClassName=org.h2.Driver --spring.datasource.username=sa --spring.datasource.password= --spring.jpa.hibernate.ddl-auto=create-drop" spring-boot:run
```

APIs (endpoints importantes)
----------------------------
Os controllers principais disponíveis:

- `GET /api/authors` — lista autores
- `GET /api/authors/{id}` — obtém autor
- `POST /api/authors` — cria autor

- `GET /api/publishers` — lista editoras
- `GET /api/publishers/{id}` — obtém editora
- `POST /api/publishers` — cria editora

- `GET /api/books` — lista obras (livros)
- `GET /api/books/{id}` — obtém obra
- `POST /api/books` — cria obra

- `GET /api/periodics` — lista periódicos

- `POST /api/loans/borrow/{bookId}?userCpf={cpf}` — empresta um livro
- `POST /api/loans/return/{bookId}` — devolve um livro

Observação: a aplicação serve uma página estática `src/main/resources/static/index.html` que consome algumas dessas APIs — a interface é mínima e serve para demonstração.

Seed de dados
-------------
Ative o seed com a flag `--app.seed.bulk=true` ao iniciar a app. O `BulkDataLoader` cria:
- ~50 autores reais/representativos
- ~50 editoras reais (lista brasileira/internacional)
- vários livros e periódicos, além de usuários (students/professors/staff)

Estrutura do projeto
--------------------
- `src/main/java/com/example/restservice/model` — entidades JPA (Author, Publisher, Book, Periodic, Student, etc.)
- `src/main/java/com/example/restservice/repository` — repositórios Spring Data JPA
- `src/main/java/com/example/restservice/controller` — controllers REST
- `src/main/java/com/example/restservice/BulkDataLoader.java` — popula o banco quando habilitado
- `src/main/resources/application.properties` — configurações (por padrão aponta para MariaDB local)

Desenvolvimento
---------------
- Para compilar sem rodar:

```fish
./mvnw -DskipTests package
```

- Para rodar os testes unitários:

```fish
./mvnw test
```

Boas práticas locais
-------------------
- Não comite credenciais no `application.properties`. Use `application-local.properties` e adicione-o ao `.gitignore` (o template já ignora `application-local.properties`).
- Caso exponha segredos acidentalmente, troque as credenciais e remova/limpe o histórico do Git (use `git filter-repo` com cuidado).

Contribuindo / Melhorias futuras
-------------------------------
- Adicionar autenticação (por exemplo, Spring Security + JWT) para endpoints de empréstimo
- Criar paginação e filtros nas listagens (`/api/books?author=...&page=1`)
- Implementar testes de integração que fazem boot com H2
- Adicionar documentação OpenAPI / Swagger para facilitar consumo

Licença
-------
Escolha uma licença adequada para seu trabalho (MIT, Apache-2.0, etc.) e adicione um arquivo `LICENSE` se desejar tornar o projeto público.

Contato
-------
GabriWar — repositório: https://github.com/GabriWar/onelibrary

---
Pequenas anotações: se quiser, eu posso também cometer esse `README.md` (e outros arquivos .gitignore) e executar o `git push` para você aqui.
