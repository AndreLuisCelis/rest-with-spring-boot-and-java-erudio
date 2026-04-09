# Visão Geral do Projeto

## Resumo do Projeto 📚

Este é um **projeto educacional de API RESTful completo** do curso Udemy "REST API's RESTFul do 0 à AWS c. Spring Boot 3 Java e Docker" por André Luis Celis.

### Arquitetura

**Backend**: Spring Boot 3 + Java 21
**Frontend**: React 17
**Banco de Dados**: MySQL 8
**Containerização**: Docker & Docker Compose

---

## Principais Funcionalidades

### 🔐 Sistema de Autenticação
- Registro e login de usuários
- Autenticação via **JWT** (JSON Web Token)
- Senhas criptografadas com PBKDF2
- Controle de acesso baseado em roles/permissões

### 👤 Gerenciamento de Pessoas
- CRUD completo
- Paginação e ordenação
- Busca por nome
- Habilitar/desabilitar registros
- Suporte a **HATEOAS** (links RESTful)
- Versionamento de API (v1 e v2)

### 📖 Gerenciamento de Livros
- CRUD completo
- Campos: autor, título, preço, data de lançamento
- Integração com frontend React

### 📄 Upload de Arquivos
- Suporte a arquivos até 200MB
- Serviço de armazenamento personalizado

### 🌐 Negociação de Conteúdo
- Suporte a **JSON**, **XML** e **YAML**
- Documentação interativa via **Swagger UI**

---

## Estrutura de Diretórios

```
📁 rest-with-spring-boot-and-java-erudio/
├── 📁 server/              # Spring Boot (Backend)
│   ├── 📁 src/main/java/com/celisapp/
│   │   ├── controllers/    # Endpoints REST
│   │   ├── service/        # Lógica de negócio
│   │   ├── model/          # Entidades JPA
│   │   ├── repositories/   # Acesso a dados
│   │   ├── security/jwt/   # Autenticação JWT
│   │   └── config/         # Configurações Spring
│   ├── 📁 src/main/resources/
│   │   ├── application.yml
│   │   └── db/migration/   # Migrações Flyway
│   └── pom.xml
│
├── 📁 client/              # React (Frontend)
│   ├── 📁 src/
│   │   ├── pages/          # Login, Books, CreateAccount
│   │   ├── services/       # Integração API (Axios)
│   │   └── routes.js
│   ├── package.json
│   └── Dockerfile
│
└── docker-compose.yml      # Orquestração Docker
```

---

## Estrutura Detalhada do Backend

### Controllers (`controllers/`)
- `PersonController.java` - Endpoints REST para gerenciamento de pessoas
- `BookController.java` - Endpoints CRUD de livros
- `AuthController.java` - Endpoints de autenticação (signin, createUser, refresh)
- `FileController.java` - Upload/download de arquivos
- `MatchController.java` - Gerenciamento de partidas

### Services (`service/`)
- `PersonService.java` - Lógica de negócio para pessoas com paginação e busca
- `BookService.java` - Operações de livros
- `AuthServices.java` - Lógica de autenticação
- `UserServices.java` - Gerenciamento de usuários
- `FileStorageService.java` - Manipulação de arquivos
- `MatchService.java` - Operações de partidas

### Models (`model/`)
- `Person.java` - Entidade pessoa com campos: id, firstName, lastName, gender, address, enabled
- `Book.java` - Entidade livro com campos: id, author, title, price, launchDate
- `User.java` - Entidade usuário implementando UserDetails do Spring Security
- `Permission.java` - Entidade de role/permissão

### Repositories (`repositories/`)
- `PersonRepository.java` - Repositório JPA estendendo PagingAndSortingRepository
- `BookRepository.java` - Repositório de livros
- `UserRepository.java` - Repositório de usuários com métodos de consulta customizados

### Configuration (`config/`)
- `SecurityConfig.java` - Configuração Spring Security, filtros JWT, CORS
- `OpenAPIConfig.java` - Configuração Swagger/OpenAPI
- `FileStorageConfig.java` - Configuração de upload de arquivos
- `WebConfig.java` - Configuração Web MVC
- `SimpleCrossFilter.java` - Filtro CORS

### Security (`security/jwt/`)
- `JwtTokenProvider.java` - Geração e validação de tokens
- `JwtTokenFilter.java` - Filtro de autenticação JWT
- `JwtConfigurer.java` - Configuração JWT

### Data Transfer Objects (`data/vo/`)
- `v1/` - DTOs versão 1 (PersonVO, etc.)
- `v2/` - DTOs versão 2 com campos adicionais (PersonVOV2 com birthday)

### Mappers (`mapper/`)
- `DozerMapper.java` - Mapeamento genérico de objetos usando Dozer
- `custom/PersonMapper.java` - Lógica customizada de mapeamento de pessoas

### Migrações de Banco de Dados (`resources/db/migration/`)
```
V1__Create_Table_Person.sql - Tabela Person
V2__Populate_Table_Person.sql - Dados de exemplo
V3__Create_Table_Books.sql - Tabela Books
V4__Insert_Data_In_Books.sql - Dados de exemplo de livros
V5__Create_Table_Permission.sql - Tabela de permissões/roles
V6__Insert_Data_In_Permission.sql - Dados de roles
V7__Create_Table_Users.sql - Tabela Users
V8__Insert_Data_In_Users.sql - Usuários de exemplo
V9__Create_Table_User_Permission.sql - Mapeamento many-to-many
V10__Insert_Data_In_User_Permission.sql - Roles de usuários
V11__Alter_Table_Person.sql - Adiciona coluna enabled
V12__Populate_Person_With_Many.sql - Mais dados de exemplo
```

---

## Estrutura do Frontend

### Pages (`src/pages/`)
- `Login/` - Componente de página de login
- `Books/` - Visualização de lista de livros
- `NewBook/` - Formulário de criar/editar livro
- `CreateAccount/` - Registro de usuário

### Services (`src/services/`)
- `api.js` - Configuração do cliente HTTP Axios com base URL do ambiente

### Routes (`src/routes.js`)
- `/` → Página de login
- `/createAccount` → Página de criar conta
- `/books` → Lista de livros
- `/book/new/:bookId` → Novo/editar livro

### Components
- `App.js` - Componente principal da aplicação
- `index.js` - Ponto de entrada React
- `global.css` - Estilos globais

---

## Tecnologias Principais

### Backend (Spring Boot 3.2.7, Java 21)

**Framework Core:**
- Spring Boot 3.2.7
- Spring Web MVC
- Spring Data JPA
- Spring Security
- Spring HATEOAS (Hypermedia As The Engine Of Application State)

**Segurança & Autenticação:**
- JWT (JSON Web Token) - java-jwt 3.18.3
- Codificação de senha PBKDF2
- JWT Token Provider e Filter

**Dados & Banco de Dados:**
- MySQL Connector (runtime)
- Flyway - Migração de banco de dados (flyway-core, flyway-mysql)
- Spring Data JPA com Hibernate
- HikariCP (connection pooling)

**Documentação de API & Negociação de Conteúdo:**
- SpringDoc OpenAPI 2.0.2 (Swagger UI)
- Suporte a formato XML Jackson
- Suporte a formato YAML Jackson
- Suporte para respostas JSON, XML e YAML

**Mapeamento de Objetos:**
- Dozer Mapper 6.4.0 (conversão de DTO)
- Mappers customizados para transformações específicas de versão

**Armazenamento de Arquivos:**
- Suporte a MultipartFile do Spring
- FileStorageService customizado

**Testes:**
- JUnit 5
- Mockito (testes unitários)
- REST Assured 4.5.0 (testes de integração)
- TestContainers 1.16.3 (testes de banco de dados containerizados)

**Ferramentas de Desenvolvimento:**
- Spring Boot DevTools
- Spring Boot Maven Plugin

### Frontend (React 17)

**Framework Core:**
- React 17.0.1
- React DOM 17.0.1
- React Router DOM 5.2.0 (roteamento client-side)

**Cliente HTTP:**
- Axios 1.7.3 (requisições API)

**UI & Estilização:**
- Bootstrap (através de framework CSS)
- React Icons 3.11.0

**Build & Desenvolvimento:**
- react-scripts 5.0.1
- Ferramentas Create React App
- NPM 10.8.2

**Testes:**
- @testing-library/react 11.1.1
- @testing-library/jest-dom 5.11.5
- @testing-library/user-event 12.2.0

---

## Arquivos de Configuração e Suas Funções

### Configuração Backend

#### `application.yml` - Configuração Spring Boot:
```yaml
file.upload-dir: Caminho do diretório de upload
security.jwt.token.secret-key: Chave de assinatura JWT
security.jwt.token.expire-length: Expiração do token (3600000ms = 1 hora)
spring.datasource.url: Conexão banco de dados MySQL
spring.datasource.username/password: Credenciais do banco
spring.jpa.open-in-view: False (melhor para microserviços)
spring.jpa.hibernate.ddl-auto: none (usando Flyway)
spring.servlet.multipart.max-file-size: 200MB
spring.servlet.multipart.max-request-size: 215MB
springdoc.pathsToMatch: Caminhos de API para documentação
```

#### `pom.xml` - Configuração de build Maven:
- Versão Java: 21
- Versão Spring Boot parent: 3.2.7
- Versões customizadas de dependências (Dozer, TestContainers, JWT, REST Assured)
- Plugins de build (Spring Boot Maven Plugin)

#### `Dockerfile` - Containerização backend:
- Imagem base: OpenJDK 21 JDK slim
- Compila JAR e executa como executável

### Configuração Frontend

#### `package.json` - Dependências e scripts NPM:
- `npm start` - Servidor de desenvolvimento (porta 3000)
- `npm run build` - Build de produção
- `npm test` - Executar testes
- Dependências: React, Axios, Router, bibliotecas de teste

#### `Dockerfile` - Containerização frontend:
- Build multi-stage (Node.js → Nginx)
- Compila bundle de produção React
- Serve via Nginx na porta 80
- Variável de ambiente: REACT_APP_API_URL

#### `nginx.conf` - Configuração do servidor web Nginx:
- Roteia requisições para app React
- Manipula arquivos estáticos
- Pode incluir configuração de proxy de API

### Orquestração Docker

#### `docker-compose.yml` - Setup multi-container:
- Serviço MySQL 8.0.29 (porta 3308)
- Serviço backend Spring Boot (porta 80, proxy para 8080)
- Serviço frontend React opcional (comentado)
- Rede compartilhada: erudio-network
- Volumes: persistência de banco e uploads de arquivos

### Controle de Versão

#### `.gitignore` - Exclui do git:
- `*.class` - Arquivos Java compilados
- `/node_modules` - Dependências NPM
- `/UploadDir` - Arquivos enviados
- `*.pem`, `*.ppk` - Chaves SSH
- Arquivos de IDE (.idea, .classpath, .project)
- `.claude` - Diretório de configuração Claude AI

---

## Endpoints da API

### Gerenciamento de Pessoas:
- `GET /api/person` - Listar todos (paginado)
- `GET /api/person/{id}` - Buscar um
- `POST /api/person` - Criar
- `PUT /api/person` - Atualizar
- `DELETE /api/person/{id}` - Deletar
- `PATCH /api/person/{id}` - Habilitar/desabilitar
- `PATCH /api/person/disable/{id}` - Desabilitar pessoa
- `PATCH /api/person/enable/{id}` - Habilitar pessoa
- `GET /api/person/findPersonByName/{firstName}` - Buscar por nome
- `GET /api/person/v2` - Endpoint V2 (com birthday)
- `POST /api/person/v2` - Criar V2

### Gerenciamento de Livros:
- `GET /api/books` - Listar todos
- `GET /api/books/{id}` - Buscar um
- `POST /api/books` - Criar
- `PUT /api/books` - Atualizar
- `DELETE /api/books/{id}` - Deletar

### Autenticação:
- `POST /auth/signin` - Login (público)
- `POST /auth/createUser` - Registrar (público)
- `POST /auth/refresh/{username}` - Renovar token

### Documentação:
- `GET /swagger-ui/` - Documentação interativa da API
- `GET /v3/api-docs/` - Especificação OpenAPI

---

## Esquema do Banco de Dados

A aplicação usa MySQL com migrações Flyway gerenciando a evolução do banco:

- **Tabela Person**: Armazena registros individuais com informações pessoais
- **Tabela Books**: Catálogo de livros com preço e data de publicação
- **Tabela Users**: Autenticação e contas de usuário com flags de segurança
- **Tabela Permissions**: Definições de roles e controle de acesso
- **User_Permission (Junção)**: Mapeamento many-to-many entre usuários e roles

---

## Modelo de Segurança

- **Autenticação**: Tokens JWT emitidos no login
- **Autorização**: Controle de acesso baseado em roles via entidade Permission
- **Hash de Senha**: PBKDF2 com HMAC-SHA256 (185000 iterações)
- **CORS**: Configurado para origens específicas
- **CSRF**: Desabilitado para API stateless
- **HTTPS**: Suportado via configuração Spring Security

---

## Arquitetura de Deploy

O projeto suporta múltiplos cenários de deploy:

1. **Desenvolvimento Local**: Executar backend na porta 8080 e frontend na porta 3000
2. **Docker Compose**: Stack completa com MySQL, backend e frontend opcional
3. **AWS Elastic Beanstalk**: Endpoints configurados em CORS e config do cliente
4. **Containerizado**: Backend (Java) e frontend (Nginx) têm Dockerfiles

---

## Como Executar

### Com Docker Compose
```bash
docker-compose up
```

### Manualmente

#### Backend (porta 8080)
```bash
cd server
./mvnw spring-boot:run
```

#### Frontend (porta 3000)
```bash
cd client
npm install
npm start
```

### Acessar a Aplicação

- **Frontend**: http://localhost:3000
- **Backend API**: http://localhost:8080
- **Swagger UI**: http://localhost:8080/swagger-ui/
- **OpenAPI Docs**: http://localhost:8080/v3/api-docs

---

## Usuários de Teste

Após executar as migrações Flyway, você terá usuários de exemplo no banco de dados. Consulte o arquivo `V8__Insert_Data_In_Users.sql` para ver os usuários disponíveis.

---

## Recursos de Aprendizado

Este projeto demonstra:

- ✅ Arquitetura REST com Spring Boot
- ✅ Autenticação e autorização JWT
- ✅ Versionamento de API
- ✅ HATEOAS
- ✅ Negociação de conteúdo (JSON/XML/YAML)
- ✅ Paginação e ordenação
- ✅ Migrações de banco com Flyway
- ✅ Testes com TestContainers e REST Assured
- ✅ Documentação com OpenAPI/Swagger
- ✅ Containerização com Docker
- ✅ Integração frontend-backend
- ✅ Upload de arquivos
- ✅ CORS e segurança web
- ✅ Mapeamento de DTOs
- ✅ Práticas de código limpo e separação de responsabilidades

---

## Conclusão

Este é um projeto completo e bem estruturado, ideal para aprender desenvolvimento de APIs RESTful modernas com práticas de segurança, versionamento, documentação e containerização! 🚀

---

**Autor**: André Luis Celis
**Curso**: REST API's RESTFul do 0 à AWS c. Spring Boot 3 Java e Docker (Udemy)
