# Repositório do curso de Spring da Alura

# Seja bem vindo ao projeto AluraForum 🔥

## O projeto 📄

AluraForum é um projeto fictício de backend disponível na plataforma [alura](https://alura.com.br/).

O projeto consiste na criação de uma **API** para um forum ( CRUD ) onde o usuário irá poder:

- Cadastrar uma pergunta
- Listar perguntas
- Fazer buscas por título de perguntas ou por pelo nome do curso
- Remove sua pergunta do forum

## Tecnologias 🧑🏽‍💻

- [Java v17](https://www.java.com/pt-BR/download/)
- [Spring](https://spring.io/)
- [H2 Database](http://www.h2database.com/html/quickstart.html)
- [Docker](https://www.docker.com/get-started/)
- [Docker Compose](https://docs.docker.com/compose/)
- [JUnit](https://junit.org/junit5/)
- [PostgreSQL](https://www.postgresql.org/)
- [PgAdmin](https://www.pgadmin.org/)
- [Swagger](https://swagger.io/)
- [Maven](https://maven.apache.org/)

## Levantando o banco de dados utilizando docker-compose 🐳

Primeiramente você precisa ter o **docker e o docker compose** instalados em sua máquina para utilizar o banco de dados.

Caso você não possua, siga está **[documentação](https://docs.docker.com/)** para realizar a instalação de ambos.

## Como utilizar 🤔

Primeiramente clone este repositório.

```
git clone https://github.com/ruanvalente/alura-forum-backend

```
Entre na pasta `backend` e utilize o maven para instalar as dependências do projeto.

Agora para levantar o banco basta utilizar o docker-compose
```
docker-compose up -d
```

## Documentação com Swagger

> Toda a documentação da aplicação pode ser encontrada acessando o recurso: *http://localhost:8080/swagger-ui.html*

<img width="1440" alt="Captura de Tela 2022-08-30 às 15 31 41" src="https://user-images.githubusercontent.com/6674232/187516013-fdd6f8cb-e96c-44d7-8dec-c699e79e01b5.png">


## Testes 🚧 - Em construção
