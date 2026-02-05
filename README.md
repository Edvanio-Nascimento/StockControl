# 🏭 Stock Control API

## Sumário
- [Descrição](#descrição)
- [Tecnologias](#tecnologias)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Endpoints Principais](#endpoints-principais)
- [DTOs Principais](#dtos-principais)
- [Tratamento de Erros](#tratamento-de-erros)
- [Como Rodar](#como-rodar)
- [Futuras Melhorias](#futuras-melhorias)

---

## Descrição
**Stock Control** é uma API RESTful para gerenciamento de produtos e controle de estoque.  

Funcionalidades principais:
- CRUD completo de produtos
- Controle de estoque (entrada e saída)
- Visualizações customizadas (`DETAILS`, `AUDIT`, `STOCK`)
- Tratamento de erros global
- DTOs separados para diferentes views do produto

---

## Tecnologias
- Java 21+
- Spring Boot 4.x
- Spring Data JPA / Hibernate
- MySQL (ou outro banco relacional)
- Maven
- Lombok (opcional)
- Spring Validation
- RESTful API com JSON
- Git para versionamento

---

## Estrutura do Projeto

src/
├─ main/
│ ├─ java/com/edy/StockControl/
│ │ ├─ controller/ → Endpoints REST
│ │ ├─ dto/ → Data Transfer Objects
│ │ ├─ entity/ → Entidades JPA
│ │ ├─ enums/ → Enums como ProductViewEnum
│ │ ├─ exception/ → Tratamento de erros global
│ │ └─ service/ → Lógica de negócio
│ └─ resources/
│ └─ application.yml → Configuração do Spring Boot

---

## Endpoints Principais

### Produtos

| Método | Endpoint | Body JSON | Response |
|--------|---------|-----------|---------|
| POST | `/v1/api/products` | ```json { "sku": "coca01l", "name": "Coca cola 1l", "description": "Coca cola 1l retornável", "price": 3.5 } ``` | `201 Created` |
| GET | `/v1/api/products` | – | ```json [ { "sku": "coca01l", "name": "Coca cola 1l", "price": 3.5, "quantity": 100 } ] ``` |
| GET | `/v1/api/products/{sku}?view=DETAILS|AUDIT|STOCK` | – | ```json { "sku": "coca01l", "name": "Coca cola 1l", "description": "...", "price": 3.5, "quantity": 100, "created": "2026-02-04T15:32:28", "updated": "2026-02-05T10:00:00", "calc": 350 } ``` |
| PATCH | `/v1/api/products/{sku}` | ```json { "price": 4.0, "description": "Nova descrição" } ``` | ```json { "sku": "coca01l", "name": "Coca cola 1l", "description": "Nova descrição", "price": 4.0, "quantity": 100, "created": "...", "updated": "...", "calc": 400 } ``` |
| DELETE | `/v1/api/products/{sku}` | – | `204 No Content` |

### Estoque

| Método | Endpoint | Body JSON | Response |
|--------|---------|-----------|---------|
| POST | `/v1/api/products/{sku}/stock/in` | ```json { "quantity": 20 } ``` | `204 No Content` ou produto atualizado |
| POST | `/v1/api/products/{sku}/stock/out` | ```json { "quantity": 10 } ``` | `204 No Content` ou produto atualizado |

---

## DTOs Principais
- `SummaryProduct` → Resumo do produto
- `DetailsProduct` → Dados completos do produto
- `AuditingProduct` → Auditoria (created, updated)
- `StockProduct` → Quantidade em estoque
- `UpdateProduct` → Atualização parcial
- `MovementProduct` → Movimentação de estoque (entrada/saída)

---

## Tratamento de Erros

| Status | Motivo | Exemplo |
|--------|--------|---------|
| 404 | Produto não encontrado | `{ "title": "Recurso não encontrado", "detail": "Produto não encontrado" }` |
| 409 | SKU duplicado | `{ "title": "Duplicidade de dados", "detail": "SKU já existe" }` |
| 422 | Violação de regra | `{ "title": "Violação de regra de negócio", "detail": "Estoque insuficiente" }` |
| 400 | Validação inválida | `{ "title": "Bad Request", "detail": "Campo obrigatório não enviado" }` |

---

## Como Rodar

1. Clone o repositório:
```bash
git clone https://github.com/Edvanio-Nascimento/StockControl
spring.datasource.url=jdbc:mysql://localhost:3306/stock_control
spring.datasource.username=root
spring.datasource.password=senha
spring.jpa.hibernate.ddl-auto=update




