# Spring Proficiency Demo 2

This project complements the main repository **spring-proficiency-demo**, demonstrating the practical use of **Spring ecosystem** technologies focused on **asynchronous processing**, **messaging**, **external integrations**, **resilience**, and **NoSQL persistence**.

The application exposes an endpoint to receive **generic audit requests**, publishes these events to a **Kafka topic**, and multiple consumers process the messages for different purposes — persistence and AI analysis.

---

## 🧾 Technologies Used

- **Spring Boot**
- **Spring Web**
- **Apache Kafka**
- **Spring Data MongoDB**
- **Spring Actuator**
- **Resilience4j (Circuit Breaker)**
- **OpenAPI documentation**
- **Docker + Docker Compose**
- **Maven**

---

## 📌 Main Features

### 📨 Messaging with Kafka
- The application receives audit data through an HTTP endpoint.
- The received payload is published to a Kafka topic.
- The service itself listens to this topic and consumes the events.

### 🗄️ Persistence with MongoDB
- Each consumed Kafka event is stored in a MongoDB collection.
- Demonstrates the integration between asynchronous messaging and NoSQL databases.

### 🤖 AI-Based Audit Analysis (Groq API)
* Another **Kafka consumer** listens to the same audit topic.
* Each event is sent to the **Groq API**, which performs an analysis of the audit content through the model provided.
* The analysis result is returned in a **structured JSON format**, demonstrating integration with generative AI services.
---

## 🚀 How to Run the Project

### ⚙️ Requirements
- Docker
- Java 17+
- Maven 3.8+

---

### ▶️ 1. Start Required Services

From the project root, run:

```bash
docker compose up -d
```

This will start:
- Apache Kafka
- MongoDB

___

### ▶️ **2. Run the application**

```bash
mvn spring-boot:run
```

The API will be available at:

```
http://localhost:8081
```

---

## 🧪 **Main Endpoints**

### 🔐 **Audit**
- `POST /api/audit` — Receives an audit request and publishes it to the Kafka topic.

---

## 📄 **License**

This project is licensed under the MIT License. Feel free to use it as reference.

---

## 👤 **Author**
**Henrique Staforti**  
GitHub: https://github.com/HenriqueStaforti