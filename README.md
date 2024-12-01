
# Gerenciador de Tarefas

Este projeto é uma aplicação Spring Boot para gerenciar tarefas. Ele oferece funcionalidades básicas de CRUD (Criar, Ler, Atualizar e Deletar) e é ideal para aprender e aplicar conceitos de desenvolvimento de APIs REST utilizando Spring Boot.

---

## 📋 Funcionalidades

- ✅ Criar uma nova tarefa.
- 📄 Listar todas as tarefas.
- ✏️ Atualizar informações de uma tarefa existente.
- 🗑️ Deletar uma tarefa.
- 📖 Documentação da API com Swagger.

---

## 🛠️ Tecnologias Utilizadas

- **Java 11**: Linguagem principal.
- **Spring Boot 2.7.x**: Framework para desenvolvimento rápido de aplicações.
  - Spring Web: Para criação de APIs REST.
  - Spring Data JPA: Para integração com banco de dados.
  - Spring Boot DevTools: Para facilitar o desenvolvimento. 
- **H2 Database**: Banco de dados em memória para desenvolvimento e testes.
- **SpringDoc OpenAi 3**: Para documentação interativa da API - Swagger.
- **Maven**: Gerenciador de dependências e build.
- **IntelliJ IDEA Community**: Ambiente de desenvolvimento.

---

## 🗂️ Estrutura do Projeto

```plaintext
src
├── main
│   ├── java
│   │   └── com.example.desafio
│   │       ├── DesafioApplication.java          # Classe principal
│   │       ├── model                            # Pacote com as classes de modelo
│   │       │   └── Tarefa.java
│   │       ├── repository                       # Pacote com os repositórios
│   │       │   └── TarefaRepository.java
│   │       ├── controller                       # Pacote com os controladores
│   │       │   ├── HelloController.java         # Endpoint de teste
│   │       │   └── TarefaController.java        # Endpoints de CRUD
│   │       ├── config                           # Pacote com configurações
│   │       │   └── SwaggerConfig.java           # Configuração do Swagger
│   ├── resources
│       ├── application.properties               # Configurações do Spring Boot
│       ├── static                               # Arquivos estáticos (não utilizado aqui)
│       └── templates                            # Templates HTML (não utilizado aqui)
├── test                                         # Testes unitários e de integração
│   └── java
│       └── com.example.desafio
│           └── DesafioApplicationTests.java
```

---

## 🚀 Como Rodar o Projeto

### Pré-requisitos

- **JDK 11** instalado ([Download](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html)).
- **Maven** instalado ([Download](https://maven.apache.org/download.cgi)).
- IntelliJ IDEA ou qualquer IDE compatível.
- Postman ou outro cliente HTTP (opcional, para testar a API).

### Passos para Rodar

1. **Clone o repositório:**

   ```bash
   git clone https://github.com/seu-usuario/seu-repositorio.git
   cd seu-repositorio
   ```

2. **Compile o projeto com Maven:**

   ```bash
   mvn clean install
   ```

3. **Execute a aplicação:**

   ```bash
   mvn spring-boot:run
   ```

4. A aplicação estará disponível em: [http://localhost:8080](http://localhost:8080)

---

## 🌐 Endpoints da API

### Tarefas

| Método | URL                | Descrição                  | Corpo da Requisição (JSON)          |
|--------|--------------------|----------------------------|--------------------------------------|
| GET    | `/tarefas`         | Lista todas as tarefas     | -                                    |
| GET    | `/tarefas/{id}`    | Retorna uma tarefa por ID  | -                                    |
| POST   | `/tarefas`         | Cria uma nova tarefa       | `{ "titulo": "Exemplo", "descricao": "Detalhes" }` |
| PUT    | `/tarefas/{id}`    | Atualiza uma tarefa        | `{ "titulo": "Novo Título", "descricao": "Novos Detalhes" }` |
| DELETE | `/tarefas/{id}`    | Deleta uma tarefa          | -                                    |

---

## 🛡️ Documentação da API com Swagger

1. Após iniciar a aplicação, acesse o Swagger:
   [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

2. Explore e teste todos os endpoints diretamente pela interface interativa do Swagger.

---

## 🗃️ Banco de Dados

O projeto utiliza o **H2 Database**, um banco de dados em memória. Os dados são apagados toda vez que a aplicação é reiniciada.

- Console do H2: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
- Credenciais:
  - **URL JDBC:** `jdbc:h2:mem:testdb`
  - **Usuário:** `sa`
  - **Senha:** *(vazia)*

---

## 📚 Recursos Adicionais

---

## ✨ Autor

Feito com ❤️ por [Ana Carolina Rodrigues](https://github.com/AnaCarolinaRodriguesLeite).  
Entre em contato: [anacarolinarodrigues480@gmail.com](anacarolinarodrigues480@gmail.com)
