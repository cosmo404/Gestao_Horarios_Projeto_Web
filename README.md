$utf8NoBom = [System.Text.UTF8Encoding]::new($false)

[System.IO.File]::WriteAllText("$PWD\README.md", @"
# Sistema de Gestão de Horários de Aula — API RESTful

API RESTful desenvolvida em **Java** com o framework **Spring Boot** para organização e gerenciamento de horários de aula de professores, contemplando o cadastro das entidades principais e consultas filtradas por docente e por turma.

Projeto desenvolvido individualmente como requisito da **Demonstração de Competência — Projeto de Website (Back-End)**.

---

## Tecnologias e Arquitetura

* **Linguagem:** Java
* **Framework:** Spring Boot (Spring Web, Spring Data JPA, Spring Security, Spring Validation)
* **Arquitetura:** Padrão MVC (*Model-View-Controller*), com separação de responsabilidades entre camadas (`model`, `repository`, `controller` e `config`)[cite: 1, 3]
* **Persistência:** Mapeamento Objeto-Relacional (ORM) via JPA / Hibernate[cite: 1, 3]
* **Segurança:** Autenticação Básica HTTP (*Basic Auth* — usuário e senha)
* **Documentação:** Swagger UI / OpenAPI (`springdoc-openapi`)[cite: 1, 3]

---

## Estrutura do Projeto

```text
src/main/java/br/unisales/gestaohorarios/
├── config/          # Configurações de segurança (Basic Auth) e Swagger
├── controller/      # Controladores REST (endpoints e validações de entrada)
├── model/           # Entidades JPA (Professor, Disciplina, Turma, Horario)
├── repository/      # Interfaces Spring Data JPA para persistência ORM
└── GestaoHorariosApplication.java
