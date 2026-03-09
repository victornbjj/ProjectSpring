# 📋 ToDo List API - Spring Boot

API REST desenvolvida em **Java com Spring Boot** para gerenciamento de tarefas (To-Do List).  
O sistema permite cadastro de usuários e gerenciamento de tarefas associadas a cada usuário, com autenticação para proteção das rotas.

Este projeto foi desenvolvido com foco em prática de **desenvolvimento backend**, **arquitetura REST** e **persistência de dados com Spring Data JPA**.

---

# 🚀 Tecnologias Utilizadas

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- Maven
- H2 Database
- REST API
- JSON

---

# 🧱 Arquitetura do Projeto

O projeto segue uma estrutura baseada em **MVC (Model-Controller-Repository)**.


src/main/java/br/com/vfit/todolist

├── task
│ ├── TaskController
│ ├── TaskModel
│ └── ITaskRepository
│
├── user
│ ├── UserController
│ ├── UserModel
│ └── IUserRepository
│
├── filter
│ └── FilterTaskAuth
│
└── TodolistApplication


---

# 📌 Funcionalidades

✔ Cadastro de usuários  
✔ Criação de tarefas  
✔ Listagem de tarefas  
✔ Associação de tarefas a usuários  
✔ Filtro de autenticação para acesso às rotas  
✔ Persistência de dados com JPA  

---

# 📡 Endpoints Principais

## 👤 Usuários

### Criar usuário

POST `/users`

Exemplo de requisição:

```json
{
  "username": "joao",
  "password": "123456"
}
✅ Tarefas
Criar tarefa

POST /tasks

{
  "title": "Estudar Spring Boot",
  "description": "Praticar desenvolvimento de APIs",
  "startAt": "2026-03-09T10:00:00",
  "endAt": "2026-03-10T18:00:00"
}
Listar tarefas

GET /tasks

Retorna todas as tarefas cadastradas para o usuário autenticado.

🔐 Autenticação

O projeto utiliza um filtro de autenticação (FilterTaskAuth) que intercepta as requisições para validar o usuário antes de permitir acesso às rotas protegidas.

⚙️ Como Executar o Projeto
1️⃣ Clonar o repositório
git clone https://github.com/seuusuario/todolist-springboot.git
2️⃣ Entrar na pasta do projeto
cd todolist
3️⃣ Executar a aplicação

Se estiver usando Maven Wrapper:

./mvnw spring-boot:run

Ou:

mvn spring-boot:run
4️⃣ Acessar a API

Servidor local:

http://localhost:8080
📚 Objetivo do Projeto

Este projeto foi desenvolvido para praticar:

Construção de APIs REST com Spring Boot

Estruturação de aplicações backend

Persistência com Spring Data JPA

Organização de código em camadas

Autenticação básica com filtros

👨‍💻 Autor

João Victor Pereira

GitHub:
https://github.com/victornbjj

LinkedIn:
https://www.linkedin.com/in/joao-victor-pereira-do-nascimento
