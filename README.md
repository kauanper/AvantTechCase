# Case Técnico - Desenvolvedor(a) de Software 
## Descrição 
Este projeto foi desenvolvido como solução para um case técnico da AvantTechJr. A aplicação consiste em um sistema de gerenciamento de tarefas, permitindo que usuários criem suas próprias contas e organizem suas atividades de forma prática e intuitiva.

Cada usuário pode cadastrar múltiplas listas, possibilitando a separação de tarefas por categorias, objetivos ou contextos específicos. Além disso, cada lista suporta diversas tarefas, que podem ser criadas, atualizadas, listadas e removidas conforme necessário, oferecendo uma experiência completa de organização pessoal.

O projeto foi desenvolvido com foco em boas práticas de desenvolvimento backend e frontend, utilizando uma arquitetura organizada, operações CRUD completas e integração entre aplicação e banco de dados, garantindo escalabilidade e facilidade de manutenção.

## Tecnologias utilizadas 

#### Backend
- Java 17
- Spring Boot
- Spring Security
- Spring Data JPA
- Hibernate
- JWT (JSON Web Token)
- Bean Validation
- Maven
- Lombok

#### Banco de Dados
- H2 Database

#### Frontend
- Thymeleaf
- Bootstrap 5
- Bootstrap Icons
- HTML5
- CSS3

## Funcionalidades 

#### Backend

A aplicação permite:

- Cadastrar uma nova lista
- Listar todas as listas
- Buscar uma lista por ID
- Atualizar os dados de uma lista
- Remover uma lista
- Cadastrar uma nova tarefa vinculada a uma lista
- Listar todas as tarefas
- Buscar uma tarefa por ID
- Atualizar os dados de uma tarefa
- Remover uma tarefa
- Registrar usuários
- Autenticar usuários utilizando JWT

#### Frontend

A aplicação permite:

- Exibir as listas criadas
- Adicionar uma nova lista
- Editar uma lista existente
- Acessar as tarefas de uma lista
- Exibir uma lista de tarefas
- Adicionar uma nova tarefa
- Editar uma tarefa existente
- Remover uma tarefa
- Alterar o status de uma tarefa

OBS: (Não integrei o front com o endpoint para deletar uma lista, então ficou apenas no backend)

## Como executar o projeto 

#### Pré-requisitos

Para executar o projeto, é necessário ter instalado:

- Java 17 ou superior
- Maven

---

## Executando a Aplicação

1. Clone ou baixe o repositório do projeto.
2. Abra o projeto em uma IDE de sua preferência, como IntelliJ IDEA ou VS Code.
3. Aguarde o Maven baixar todas as dependências do projeto.
4. Execute a classe principal do Spring Boot.
5. Após iniciar, a aplicação estará disponível no navegador.

---

## Acessando a Aplicação

Para registrar um novo usuário, utilize uma ferramenta de testes de API, como Postman ou Insomnia.

#### Endpoint

```http
POST http://localhost:8080/user/register
```

#### Corpo da Requisição

```json
{
  "name": "Seu Nome",
  "login": "Seu Login",
  "password": "suasenha"
}
```

Após isso, você pode usar livremente abrindo seu navegador e acessando:

```txt
http://localhost:8080/view/login
```

Entre com seu login e senha

 
## Decisões tomadas 
Explique decisões importantes, como: - como listas e tarefas se relacionam; - o que acontece ao remover uma lista com tarefas; - como os dados são armazenados.  
## Observações 
Comente decisões tomadas, dificuldades ou melhorias futuras. 
