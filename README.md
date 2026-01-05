# FarmTech API

Serviço de backend para gerenciamento de produtos agrícolas e produtores. Este projeto foi desenvolvido como um desafio técnico em construção de APIs RESTful com Spring Boot e boas práticas de tratamento de erros.

## Tecnologias

* Java 17
* Spring Boot 3
* Spring Data JPA
* H2 Database (Banco em memória)
* SpringDoc OpenAPI (Swagger)
* JUnit 5 & Mockito

## Destaques da Implementação

Além do CRUD básico, o projeto implementa padrões de mercado:

* **Tratamento Global de Exceções:** Implementação de `@RestControllerAdvice` para centralizar a captura de erros. Isso garante que a API retorne status codes HTTP adequados (como 409 para conflitos ou 400 para erros de validação) e mensagens JSON padronizadas.
* **Testes de Integração:** Testes automatizados utilizando `MockMvc` para validar o fluxo completo dos controladores, garantindo que regras de negócio estejam funcionando conforme o esperado.
* **Documentação Swagger:** Interface visual para testar os endpoints sem necessidade de ferramentas externas.

## Como Executar

Pré-requisitos: Java 17 e Maven.

1. Clone este repositório.
2. Na raiz do projeto, execute o comando:
   ```bash
   mvn spring-boot:run

## Documentação da API (Swagger UI)

Com a aplicação rodando, acesse a documentação interativa:

**[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)**
