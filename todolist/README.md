# Todo List API

API REST para gerenciamento de tarefas pessoais, desenvolvida com Spring Boot. Permite criar usuários, autenticar via Basic Auth e gerenciar tarefas com controle de datas e prioridades.

---

## Tecnologias utilizadas

- **Java 17**
- **Spring Boot 3.5.11**
- **Spring Web** — construção da API REST com controllers, mapeamento de rotas e serialização JSON
- **Spring Data JPA** — abstração do acesso ao banco de dados via repositórios e entidades JPA
- **H2 Database** — banco de dados relacional em memória, utilizado no ambiente de desenvolvimento e testes. Os dados são reiniciados a cada execução da aplicação
- **Hibernate** — ORM responsável pelo mapeamento entre as entidades Java e as tabelas do banco de dados
- **Lombok** — redução de código boilerplate através de anotações como `@Data`, que gera automaticamente getters, setters, `equals`, `hashCode` e `toString`
- **BCrypt (Favre)** — algoritmo de hash unidirecional utilizado para criptografar as senhas dos usuários antes de armazená-las no banco
- **Springdoc OpenAPI / Swagger UI** — geração automática da documentação interativa da API a partir das anotações do código
- **JaCoCo** — ferramenta de análise de cobertura de testes, gera relatório HTML em `target/site/jacoco/index.html`

---

## Arquitetura do projeto

```
todolist/
├── src/
│   ├── main/
│   │   ├── java/br/com/vfit/todolist/
│   │   │   ├── errors/
│   │   │   │   └── ExceptionHandlerController.java   # Tratamento global de exceções
│   │   │   ├── filter/
│   │   │   │   └── FIlterTaskAuth.java               # Filtro de autenticação Basic Auth
│   │   │   ├── task/
│   │   │   │   ├── ITaskRepository.java              # Repositório JPA de tarefas
│   │   │   │   ├── TaskController.java               # Endpoints de tarefas
│   │   │   │   └── TaskModel.java                    # Entidade de tarefa
│   │   │   ├── user/
│   │   │   │   ├── IUserRepository.java              # Repositório JPA de usuários
│   │   │   │   ├── UserController.java               # Endpoints de usuários
│   │   │   │   └── UserModel.java                    # Entidade de usuário
│   │   │   └── utils/
│   │   │       └── Ultils.java                       # Utilitário para cópia de propriedades
│   │   └── resources/
│   │       └── application.properties                # Configurações da aplicação
│   └── test/
│       └── java/br/com/vfit/todolist/
│           └── UserControllerTest.java               # Testes automatizados
```

---

## Como executar localmente

### Pré-requisitos

- Java 17 ou superior
- Maven 3.8+

### Passos

```bash
# Clone o repositório
git clone https://github.com/seu-usuario/todolist.git

# Acesse a pasta do projeto
cd todolist

# Execute a aplicação
mvn spring-boot:run
```

A aplicação estará disponível em `http://localhost:8080`.

---

## Documentação da API

Com a aplicação em execução, acesse a documentação interativa gerada pelo Swagger UI:

```
http://localhost:8080/swagger-ui.html
```

A especificação OpenAPI em formato JSON também está disponível em:

```
http://localhost:8080/v3/api-docs
```

---

## Endpoints

### Usuários

| Método | Rota | Descrição | Autenticação |
|--------|------|-----------|--------------|
| POST | `/users/` | Cria um novo usuário | Não |

**Exemplo de requisição — criar usuário:**

```json
POST /users/
Content-Type: application/json

{
  "name": "João Silva",
  "username": "joaosilva",
  "password": "minhasenha"
}
```

---

### Tarefas

Todos os endpoints de tarefas exigem autenticação via **Basic Auth** (username e senha cadastrados).

| Método | Rota | Descrição |
|--------|------|-----------|
| POST | `/tasks/` | Cria uma nova tarefa |
| GET | `/tasks/` | Lista todas as tarefas do usuário autenticado |
| PUT | `/tasks/{id}` | Atualiza uma tarefa existente |

**Exemplo de requisição — criar tarefa:**

```json
POST /tasks/
Authorization: Basic <credenciais>
Content-Type: application/json

{
  "title": "Reunião de sprint",
  "description": "Reunião de planejamento da sprint 12",
  "priority": "ALTA",
  "startAt": "2030-01-01T10:00:00",
  "endAt": "2030-01-01T11:00:00"
}
```

**Regras de negócio:**

- O campo `title` aceita no máximo 50 caracteres
- As datas `startAt` e `endAt` devem ser futuras em relação à data atual
- A data de início deve ser anterior à data de término
- Um usuário só pode alterar suas próprias tarefas

---

## Autenticação

A API utiliza **HTTP Basic Authentication**. As credenciais são enviadas no cabeçalho de cada requisição às rotas de tarefas:

```
Authorization: Basic <base64(username:password)>
```

As senhas são armazenadas com hash **BCrypt** (custo 12), garantindo que nunca sejam salvas em texto puro no banco de dados.

---

## Banco de dados

Em ambiente de desenvolvimento, a aplicação utiliza o **H2 Database** em memória. O console do H2 pode ser acessado em:

```
http://localhost:8080/h2-console
```

| Configuração | Valor |
|---|---|
| JDBC URL | `jdbc:h2:mem:todolistdb` |
| Username | `admin` |
| Password | `admin` |

> Os dados são apagados a cada reinicialização da aplicação.

---

## Testes

Para executar os testes automatizados:

```bash
mvn test
```

Para gerar o relatório de cobertura com JaCoCo:

```bash
mvn verify
```

O relatório HTML será gerado em:

```
target/site/jacoco/index.html
```

---

## Deploy

A aplicação está configurada para deploy na plataforma **Render**. Para ambientes de produção, recomenda-se substituir o H2 por um banco de dados persistente como PostgreSQL, atualizando as configurações em `application.properties`.

---

## Licença

Este projeto foi desenvolvido para fins educacionais.