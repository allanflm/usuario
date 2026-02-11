# Microsserviço de Gerenciamento de Usuários 🚀

Este microsserviço foi desenvolvido para fornecer uma estrutura robusta de gerenciamento de usuários, focando em segurança e escalabilidade. A aplicação utiliza o padrão REST para gerenciar perfis, endereços e telefones, tudo protegido por autenticação JWT.

## 🛠️ Tecnologias e Ferramentas

* **Linguagem:** Java 17
* **Framework:** Spring Boot 3.x
* **Segurança:** Spring Security & JWT (JSON Web Token)
* **Persistência:** Spring Data JPA & PostgreSQL
* **Produtividade:** Lombok
* **Gerenciador de Dependências:** Gradle

## 🏗️ Arquitetura e Boas Práticas

O projeto foi estruturado seguindo padrões de mercado para garantir manutenibilidade:

* **Stateless Authentication:** Uso de JWT para garantir que a API seja escalável e não dependa de estado de sessão no servidor.
* **Padrão DTO:** Utilização de Data Transfer Objects para isolar a camada de persistência da camada de apresentação, evitando a exposição desnecessária das entidades de banco de dados.
* **Injeção de Dependências:** Uso de `@RequiredArgsConstructor` do Lombok para injeção via construtor, facilitando testes unitários e garantindo imutabilidade.
* **Segurança de Dados:** Senhas e informações sensíveis são protegidas através das configurações do Spring Security.

## 🚀 Funcionalidades Principais

* **Cadastro de Usuários:** Registro completo com validação.
* **Autenticação:** Endpoint `/usuario/login` que retorna um Bearer Token.
* **Gestão de Contatos:** Cadastro e atualização de múltiplos endereços e telefones vinculados ao perfil.
* **Busca Refinada:** Recuperação de dados de usuário via e-mail com validação de token.

## 🔧 Como Executar o Projeto

1.  **Clone o repositório:**
    ```bash
    git clone [https://github.com/allanflm/usuario.git](https://github.com/allanflm/usuario.git)
    ```

2.  **Configure o Banco de Dados:**
    Certifique-se de ter um banco PostgreSQL rodando e ajuste as credenciais no arquivo `src/main/resources/application.properties`.

3.  **Compile e execute:**
    ```bash
    ./gradlew bootRun
    ```

4.  **Acesse a API:**
    A aplicação estará disponível em `http://localhost:8080`.

---
Desenvolvido por [Allan](https://github.com/allanflm)
