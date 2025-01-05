# REST with Spring Boot and Java - Erudio

Este projeto consiste em uma aplicação completa de API RESTful utilizando Spring Boot para o back-end e React para o front-end, desenvolvido através do curso [REST API's RESTFul do 0 à AWS c. Spring Boot 3 Java e Docker](https://www.udemy.com/course/restful-apis-do-0-a-nuvem-com-springboot-e-docker/?couponCode=SKILLS4SALEA). Ele serve como um exemplo prático para ensinar conceitos e práticas recomendadas de desenvolvimento de APIs em Java e interfaces de usuário em React.

## Tecnologias Utilizadas

- **Back-end**:
  - **Java 17**: Linguagem de programação.
  - **Spring Boot**: Framework para facilitar a criação de aplicativos Java.
  - **Spring Data JPA**: Para interações com bancos de dados.
  - **Maven**: Gerenciamento de dependências e construção do projeto.
  - **Docker**: Para containerização da aplicação.
  - **Swagger UI**: Documentação interativa da API.

- **Front-end**:
  - **React**: Biblioteca JavaScript para construção de interfaces de usuário.
  - **Axios**: Para fazer requisições HTTP para a API.
  - **React Router**: Para gerenciamento de rotas no front-end.
  - **Bootstrap**: Para estilização e componentes responsivos.

## Pré-requisitos

- **Java JDK 17**: Certifique-se de ter o JDK instalado.
- **Node.js e npm**: Necessário para rodar o projeto React.
- **Maven**: Necessário para construir o projeto Spring Boot.
- **Docker** (opcional): Para rodar a aplicação em um container.

## Instalação e Execução

### Back-end (Spring Boot)

1. **Clone o repositório**

    ```bash
    git clone https://github.com/AndreLuisCelis/rest-with-spring-boot-and-java-erudio.git
    cd rest-with-spring-boot-and-java-erudio
    ```

2. **Construir o Projeto com Maven**

    Execute o comando abaixo para compilar e construir o projeto:

    ```bash
    mvn clean install
    ```

3. **Executar a Aplicação**

    Após a construção, você pode rodar a aplicação com o seguinte comando:

    ```bash
    mvn spring-boot:run
    ```

    A aplicação estará disponível em: `http://localhost:8080`

### Front-end (React)

1. **Navegue até a pasta do client**

    ```bash
    cd client
    ```

2. **Instalar Dependências**

    Instale as dependências do projeto React usando npm:

    ```bash
    npm install
    ```

3. **Executar a Aplicação React**

    Após a instalação das dependências, execute a aplicação com:

    ```bash
    npm start
    ```

    A aplicação React estará disponível em: `http://localhost:3000`

### Executando com Docker

1. **Construir a Imagem Docker do Back-end**

    ```bash
    docker build -t rest-with-spring-boot-erudio .
    ```

2. **Executar o Container do Back-end**

    ```bash
    docker run -p 8080:8080 rest-with-spring-boot-erudio
    ```

    A aplicação back-end estará disponível em: `http://localhost:8080`

3. **Docker para o Front-end** (Opcional)

    Se desejar, você pode criar um Dockerfile para o projeto React e construir uma imagem Docker para o front-end, seguindo um processo semelhante ao descrito acima para o back-end.

## Uso

- **Documentação da API**: Após iniciar a aplicação back-end, você pode acessar a documentação interativa da API via Swagger UI em: `http://localhost:8080/swagger-ui.html`.
- **Interface de Usuário**: Inicie o front-end React para interagir com a API de maneira amigável ao usuário.

## Licença

Este projeto é licenciado sob a Licença MIT - veja o arquivo [LICENSE](LICENSE) para detalhes.

## Autor

- **André Luis Celis** - [GitHub Profile](https://github.com/AndreLuisCelis)

## Contato

Para dúvidas ou mais informações, entre em contato via [LinkedIn](https://www.linkedin.com/in/andre-luis-celis/).
