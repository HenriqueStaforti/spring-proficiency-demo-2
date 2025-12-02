# Spring Proficiency Demo 2

Este projeto complementa o repositório principal **spring-proficiency-demo**, demonstrando o uso prático de outras tecnologias do ecossistema **Spring** voltadas para **processamento assíncrono**, **mensageria** e **persistência NoSQL**.

A aplicação expõe uma rota para receber **requisições genéricas de auditoria**, publica esses eventos em um tópico **Kafka**, e o próprio serviço consome a mensagem para então persistir os dados em um banco **MongoDB** — tudo com propósito demonstrativo.

---

## 🧾 Tecnologias Utilizadas

- **Spring Boot**
- **Spring Web**
- **Apache Kafka**
- **Spring Data MongoDB**
- **OpenAPI documentation**
- **Lombok**
- **Docker + Docker Compose**
- **Maven**

---

## 📌 Principais Funcionalidades

### 📨 Produção e Consumo de Mensagens (Kafka)
- A aplicação recebe dados de auditoria via uma rota HTTP.
- O conteúdo recebido é publicado em um tópico Kafka.
- O próprio serviço escuta esse tópico e consome os eventos.

### 🗄️ Persistência em MongoDB
- Cada evento consumido do Kafka é persistido em uma coleção MongoDB.
- Demonstra integração entre mensageria assíncrona e banco NoSQL.

---

## 🚀 Como Rodar o Projeto

### ⚙️ Pré-requisitos
- Docker
- Java 17+
- Maven 3.8+

---

### ▶️ 1. Subir os serviços necessários

Na raiz do projeto execute:

```bash
docker compose up -d
```

Isso irá subir:
- Apache Kafka
- MongoDB

___

### ▶️ **2. Executar a aplicação**

```bash
mvn spring-boot:run
```

A API estará disponível em:

```
http://localhost:8081
```

---

## 🧪 **Endpoints Principais**

### 🔐 **Auditoria**
- `POST /api/audit` — Recebe um request de auditoria e publica no tópico Kafka.

---

## 📄 **Licença**

Este projeto está sob licença MIT. Sinta‑se à vontade para utilizar como referência.

---

## 👤 **Autor**
**Henrique Staforti**  
GitHub: https://github.com/HenriqueStaforti