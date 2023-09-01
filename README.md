# Sistema de Gerenciamento de Estoque 🖥️

## Descrição

Este projeto consiste em um Sistema de Gerenciamento de Estoque desenvolvido utilizando o Spring Framework, incluindo o Spring Boot e o Spring Data JPA, juntamente com um banco de dados PostgreSQL. O objetivo principal é permitir a criação, atualização, leitura e exclusão de produtos no estoque por meio de requisições HTTP. As interações com o sistema serão simuladas usando o Postman.

## Requisitos

- Utilização do Spring Framework (Spring Boot, Spring Data JPA) para criar a aplicação.
- Uso de um banco de dados PostgreSQL ou H2 para armazenar os dados dos produtos.
- Criação de endpoints REST para as operações CRUD (criação, leitura, atualização, exclusão) de produtos.
- Permitir a criação de novos produtos com informações como nome, descrição, preço e quantidade que deseja armazenar.
- Possibilitar a atualização dos detalhes de um produto existente.
- Permitir a obtenção da lista de todos os produtos ou de um produto específico por ID ou nome.
- Possibilitar a exclusão de produtos.
- Implementação de tratamento de erros e exceções apropriados.
- Implementação de testes unitários com cobertura de 100%.
- Documentação da API com o Swagger, tornando-a compreensível para outros desenvolvedores.

## Documentação da API

A documentação da API pode ser acessada via Swagger no seguinte link: [Documentação Swagger](http://localhost:8080/swagger-ui/index.html#/)

## Como Usar

1. Clone o repositório para a sua máquina local.
2. Configure o banco de dados PostgreSQL de acordo com suas preferências no arquivo `application.properties`.
3. Execute a aplicação Spring Boot.
4. Utilize o Postman ou outra ferramenta de sua escolha para realizar requisições HTTP aos endpoints REST definidos na API.
5. Consulte a documentação Swagger para obter detalhes sobre os endpoints disponíveis e seus parâmetros.

## Contribuição

Contribuições são bem-vindas! Se você deseja melhorar este projeto, sinta-se à vontade para abrir um Pull Request ou reportar problemas abrindo uma Issue.


