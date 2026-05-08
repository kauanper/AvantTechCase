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

#### Executando a Aplicação

1. Clone ou baixe o repositório do projeto.
2. Abra o projeto em uma IDE de sua preferência, como IntelliJ IDEA ou VS Code.
3. Aguarde o Maven baixar todas as dependências do projeto.
4. Execute a classe principal do Spring Boot.
5. Após iniciar, a aplicação estará disponível no navegador.

---

#### Acessando a Aplicação

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

#### Modelagem de Dados: Relação Um-para-Muitos (1:N)
A aplicação foi estruturada para que exista uma hierarquia clara entre os dados:
- Decisão: Uma Lista funciona como um contêiner pai para múltiplas Tarefas.
- Lógica: Implementamos a anotação @OneToMany no Java. Isso significa que cada tarefa possui uma chave estrangeira apontando para sua lista de origem.
- Objetivo: Permitir que o usuário organize diferentes contextos de vida ou trabalho (ex: "Compras", "Faculdade", "Trabalho") de forma isolada e organizada.

#### Integridade Referencial: Exclusão em Cascata (Cascade Delete)
Uma decisão crítica para a saúde do banco de dados foi o comportamento de remoção.
- Decisão: Ao excluir uma Lista, o sistema remove automaticamente todas as Tarefas vinculadas a ela.
- Lógica: Configuramos o cascade = CascadeType.ALL e orphanRemoval = true.
- Objetivo: Evitar "dados órfãos". Em um sistema de gerenciamento, uma tarefa não faz sentido sem o seu contexto (Lista). Isso garante que o banco de dados permaneça limpo e consistente, sem registros inúteis.

#### Persistência de Dados: Banco de Dados Relacional e Spring Data JPA
Para garantir que as informações não sejam perdidas ao desligar o servidor, focamos em persistência sólida.
- Decisão: Uso de banco de dados SQL (como H2 para testes ou PostgreSQL/MySQL para produção).
- Lógica: Utilizamos o Spring Data JPA (Java Persistence API). Ele atua como uma ponte que transforma nossos objetos Java diretamente em tabelas no banco.
- Objetivo: Segurança e escalabilidade. Bancos relacionais são excelentes para lidar com as conexões entre usuários, listas e tarefas, garantindo que os dados sejam salvos de forma estruturada.

#### Renderização de Interface: Server-Side Rendering (SSR) com Thymeleaf
Escolhemos processar a interface no servidor antes de enviá-la ao navegador.
- Decisão: Utilizar o motor de templates Thymeleaf em vez de um framework de front-end separado (como React ou Vue).
- Lógica: O servidor Java lê o HTML, processa as condicionais (como as cores dos status) e entrega o HTML final para o usuário.
- Objetivo: Simplicidade e Performance Inicial. Como o HTML já chega pronto, a página carrega mais rápido e a lógica de negócio (como quem pode ver qual tarefa) fica protegida dentro do servidor, não exposta no navegador.

#### Feedback Visual e UX (Experiência do Usuário)
A interface foi projetada para ser comunicativa sem a necessidade de leitura constante de textos.
- Decisão: Cores semânticas baseadas no status (PENDENTE = Vermelho, EM_ANDAMENTO = Laranja, CONCLUIDA = Verde).
- Lógica: Uso da classe th:classappend do Thymeleaf para injetar CSS dinamicamente.
- Objetivo: Reduzir a carga cognitiva do usuário, permitindo que ele entenda a prioridade e o estado da sua lista de tarefas com apenas um olhar rápido.

 
## Observações 

#### Decisões de Desenvolvimento

Algumas decisões arquiteturais e tecnológicas foram tomadas principalmente visando agilidade no desenvolvimento do projeto, considerando o tempo disponível para implementação.

---

#### Escolha do H2 Database e Thymeleaf

Foi utilizado o banco de dados H2 por ser leve, simples de configurar e ideal para ambientes de desenvolvimento e testes.

No frontend, optei pelo uso do Thymeleaf, principalmente pela sua integração nativa com o Spring Boot, permitindo uma construção mais rápida da interface. Essa abordagem também facilitou o reaproveitamento dos endpoints já existentes da API, acelerando significativamente o desenvolvimento da aplicação web.

---

#### Implementação de Autenticação JWT

Como diferencial do projeto, decidi implementar autenticação utilizando JWT (JSON Web Token).

Além de adicionar uma camada de segurança à aplicação, essa decisão também teve como objetivo fortalecer meus estudos e prática com autenticação e autorização em aplicações backend.

Por conta disso, foi criada uma nova entidade `User`, juntamente com endpoints adicionais para registro e autenticação de usuários.

---

#### Organização e Arquitetura

Durante o desenvolvimento, procurei manter uma arquitetura organizada e de fácil manutenção, aplicando conceitos inspirados em Clean Code e separação de responsabilidades.

Os módulos da aplicação foram divididos de forma a manter serviços menores, mais reutilizáveis e com responsabilidades únicas, facilitando a legibilidade e evolução futura do sistema.

---

#### Foco Maior no Backend

Inicialmente, interpretei o desafio como sendo focado apenas em uma das frentes da aplicação. Por gostar muito mais da área de backend, acabei escolhendo dedicar a maior parte do meu tempo e esforço nessa parte do sistema.

Por esse motivo, concentrei bastante atenção na estrutura da API, autenticação, organização da arquitetura e modelagem das entidades.

Posteriormente, ao perceber que o projeto exigia tanto backend quanto frontend, precisei acelerar a construção da interface web, o que acabou tornando o desenvolvimento do frontend mais corrido em comparação ao restante da aplicação.## Observações 
Comente decisões tomadas, dificuldades ou melhorias futuras. 
