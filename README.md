# FokaBlog API 🚀

![Java](https://img.shields.io/badge/Java-21-darkblue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.4-darkblue)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-darkblue)

### Bem-vindo ao FokaBlog!

Este projeto nasceu da minha jornada de aprofundamento em desenvolvimento back-end com Java e o ecossistema Spring. O objetivo era criar não apenas uma API funcional para um blog, mas uma base sólida, segura e escalável, aplicando as melhores práticas do mercado.

O foco principal foi a construção de um sistema de segurança robusto do zero, que é um pilar essencial para qualquer aplicação web moderna.

---

### ✨ Features Principais

* 🔒 **Segurança Robusta com Spring Security:** Implementação completa de um sistema de segurança que gerencia a autenticação de usuários e a autorização de acesso aos endpoints.

* 👤 **Autenticação e Autorização por Roles:** O sistema utiliza login baseado em sessão e controla o que cada usuário pode fazer através de permissões (`Roles`), como `ROLE_USER` para usuários comuns e `ROLE_ADMIN` para administradores.

* ✍️ **CRUD Completo:** API RESTful com todas as operações de Criar, Ler, Atualizar e Deletar para as entidades principais: Posts, Comentários e Usuários.

* 🏗️ **Boas Práticas de Código:** A arquitetura foi pensada para ser limpa e de fácil manutenção, utilizando uma estrutura em camadas (Controllers, Services, Repositories) e o padrão **DTO (Data Transfer Object)** para um contrato de API seguro e bem definido.

---

### 💻 A Jornada de Aprendizado

O maior desafio (e também o maior aprendizado!) foi, sem dúvida, a implementação da camada de segurança com Spring Security. Configurar o `SecurityFilterChain`, entender o fluxo de autenticação, gerenciar as permissões e fazer tudo funcionar de forma coesa foi uma experiência incrível que solidificou conceitos essenciais de desenvolvimento seguro de APIs.

---

###  toolbox: Caixa de Ferramentas (Tecnologias)

* **Java** `21`
* **Spring Boot** `3.5.4`
* **Spring Security** (com autenticação por sessão)
* **Spring Data JPA / Hibernate**
* **PostgreSQL** (gerenciado com pgAdmin 4)
* **Maven** para gerenciamento de dependências
* **Lombok** para um código mais limpo

---

### ⚙️ Como Executar

1.  **Clone o repositório:**
    ```bash
    git clone [https://github.com/GilAndrey/FokaBlog.git](https://github.com/GilAndrey/FokaBlog.git)
    cd FokaBlog
    ```

2.  **Configure o `application.properties`:**
    Aponte para o seu banco de dados PostgreSQL local.
    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/fokablog_db
    spring.datasource.username=seu_usuario_postgres
    spring.datasource.password=sua_senha_postgres
    spring.jpa.hibernate.ddl-auto=update
    ```

3.  **Execute a aplicação:**
    ```bash
    mvn spring-boot:run
    ```
    A API estará no ar em `http://localhost:8080`.

---

### 🔮 Próximos Passos (Possíveis Updates)

* Implementar autenticação via **Token JWT** como uma alternativa à sessão.
* Adicionar **validação de dados** (`@Valid`) nos DTOs para requisições mais robustas.
* Refinar as regras de permissão (ex: um usuário só pode editar os próprios posts).
* Construir um **front-end em React ou Vue.js** para consumir a API.

---
*Desenvolvido por Gil Andrey.*
