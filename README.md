# FokaBlog API 🚀

![Java](https://img.shields.io/badge/Java-21-darkblue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.4-darkblue)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-darkblue)

Backend para uma plataforma de blog moderna, desenvolvido com Java e Spring Boot. Este projeto foi criado como uma demonstração prática de construção de APIs REST seguras, aplicando conceitos fundamentais de autenticação e autorização com Spring Security.

---

### ✨ Features Principais

* 🔒 **Segurança Robusta:** Implementação completa com **Spring Security** para autenticação baseada em sessão e autorização por `Roles` (`USER` e `ADMIN`).

* 👤 **Gerenciamento de Usuários:** API para registro e login, com criptografia de senhas usando **BCrypt** para garantir a segurança dos dados.

* ✍️ **CRUD de Conteúdo:** Endpoints RESTful para gerenciar Posts e Comentários de forma eficiente.

* 🏗️ **Boas Práticas:** Arquitetura limpa em camadas (Controller, Service, Repository) e o uso de **DTOs** para um contrato de API seguro e bem definido.

---

### 🔧 Tecnologias Utilizadas

* **Java** `21`
* **Spring Boot** `3.5.4`
* **Spring Security**
* **Spring Data JPA / Hibernate**
* **Maven**
* **PostgreSQL**
* **Lombok**

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
    spring.datasource.url=jdbc:postgresql://localhost:5432/fokablog
    spring.datasource.username=seu_usuario_postgres
    spring.datasource.password=sua_senha_postgres
    spring.jpa.hibernate.ddl-auto=update
    ```

3.  **Execute a aplicação:**
    ```bash
    mvn spring-boot:run
    ```
    A API estará disponível em `http://localhost:8080`.

---

### 🔮 Possíveis Updates (Próximos Passos)

* Implementar autenticação via **Token JWT** como uma alternativa à sessão.
* Adicionar **validação de dados** (`@Valid`) nos DTOs para requisições mais robustas.
* Refinar as regras de permissão (ex: um usuário só pode editar os próprios posts).
* Construir um **front-end em React ou Vue.js** para consumir a API.

---
*Desenvolvido por Gil Andrey. <3*
