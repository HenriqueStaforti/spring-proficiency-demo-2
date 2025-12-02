# Spring Proficiency Demo 2

This project complements the main repository **spring-proficiency-demo** by showcasing additional technologies from the **Spring ecosystem**, focused on **asynchronous processing**, **messaging**, and **NoSQL persistence**.

The application exposes an endpoint to receive **generic audit requests**, publishes these events to a **Kafka** topic, and the same service consumes the message to persist the data into **MongoDB** — all for demonstration purposes.

---

## 🧾 Technologies Used

- **Spring Boot**
- **Spring Web**
- **Spring for Apache Kafka**
- **Spring Data MongoDB**
- **Lombok**
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