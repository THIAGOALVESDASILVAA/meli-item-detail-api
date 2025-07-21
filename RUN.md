# 🚀 Meli Item Detail API – Setup Guide

## 📋 Prerequisites

- **Java 17**
- **Maven 3.6+**
- **Docker** (optional)
- **Git**

---

## 🏗️ Project Architecture

This project follows a **Hexagonal Architecture** in a multi‑module Maven structure:

```
meli-item-detail-api/
├── core/                    # Domain & business rules
├── app/                     # Use cases & application services
├── infrastructure/          # Adapters (mock JSON data, Redis configs)
└── rest-adapter/            # REST controllers (port 8080)
```

---

## 🎯 Executable Applications

### 1. REST API (port 8080)

Run with:

```bash
mvn spring-boot:run -pl rest-adapter
```

---

## 🏃‍♂️ Quick Start

### Clone and Build

```bash
git clone https://github.com/THIAGOALVESDASILVAA/meli-item-detail-api.git
cd meli-item-detail-api
# Build the entire project
mvn clean install
```

### Run the REST API

```bash
# Directly with Maven
mvn spring-boot:run -pl rest-adapter

# Or using the packaged JAR
java -jar rest-adapter/target/meli-item-detail-rest-adapter-1.0-SNAPSHOT.jar
```

### Verify the Setup

```bash
# Health check
curl http://localhost:8080/actuator/health

# List products by brand
curl http://localhost:8080/brands/Apple/products

# Get product details
curl http://localhost:8080/products/MLB123456789
```


---

## 🛠️ Development

### Run Tests

```bash
# All tests
mvn test

# Tests by module
mvn test -pl core
mvn test -pl app
mvn test -pl rest-adapter
```

### Run with a Specific Profile

```bash
# Run REST adapter with its profile
mvn spring-boot:run -pl rest-adapter -Dspring-boot.run.profiles=rest-adapter
```

### Clean and Rebuild

```bash
# Clean and compile
mvn clean compile

# Full rebuild (skip tests)
mvn clean install -DskipTests
```

---

## 📡 API Endpoints

### Products

- `GET /products/{id}` — Product details
- `GET /products/{id}/images` — Product images
- `GET /products/{id}/reviews` — Product reviews
- `GET /products/{id}/variations` — Product variations
- `GET /products/{id}/promotions` — Product promotions
- `GET /products/{id}/technical-specifications` — Technical specifications
- `GET /products/{id}/related` — Related products

### Brands

- `GET /brands/{brand}/products` — Products by brand

### Cart

- `POST /cart/add` — Add item to cart

### Payments

- `GET /products/{id}/payment-options` — Payment methods

### Monitoring

- `GET /actuator/health` — Health check
- `GET /actuator/info` — Application info
- `GET /swagger-ui.html` — API documentation

---

## ⚙️ Configuration

- **Default Ports**: REST API on 8080
- **Available Profiles**: `rest-adapter`
- **Mock Data**: Stored at `infrastructure/src/main/resources/mock-data/`

---

## 🐛 Troubleshooting

### Port Already in Use

```bash
lsof -ti:8080
kill -9 $(lsof -ti:8080)
```

### Compilation Errors

```bash
mvn dependency:purge-local-repository
mvn clean install -U
```

### Failing Tests

```bash
mvn test -X
mvn clean install -DskipTests
```

---

## 📊 Monitoring and Logs

### View Logs

```bash
tail -f rest-adapter/logs/meli-item-detail-api.log
```

### Metrics

```bash
curl http://localhost:8080/actuator/metrics
curl http://localhost:8080/actuator/prometheus
```

---

## 🚀 Build and Deploy

```bash
mvn clean install -Dmaven.test.skip=true -Dspring.profiles.active=prod
```

---

## 📚 Additional Documentation

- **Swagger UI**: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- **API Docs**: [http://localhost:8080/api-docs](http://localhost:8080/api-docs)
- **Architecture**: Hexagonal + Multi‑module Maven
- **Framework**: Spring Boot 3.2.0 + Java 17
- **Runbook**: `docs/RUNBOOK.md` – Emergency procedures

---

## 📝 Notes

- **Cache**: In-memory (ConcurrentHashMap)
- **Data**: Mock JSON files
- **Tests**: 101+ tests with 93% success rate

