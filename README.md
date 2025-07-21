# 🛒 Meli Item Detail API

A RESTful API for MercadoLibre item details, built with **Hexagonal Architecture** and **Spring Boot 3.2.0**.

---

## 📋 Documentation

| Document | Purpose                      | Audience     |
| -------- | ---------------------------- | ------------ |
| `RUN.md` | 🚀 Complete setup guide      | Developers   |
| `docs/`  | 📚 Operational documentation | SRE / DevOps |

---

## 🏗️ Architecture

```
meli-item-detail-api/
├── core/                    # 🎯 Domain & business rules
├── app/                     # 🔧 Use cases & application services
├── infrastructure/          # 🗄️ Adapters (JSON mocks, configs)
└── rest-adapter/            # 🌐 REST controllers (port 8080)
```

---

## 🚀 Quick Start

### Clone and build

```bash
git clone https://github.com/THIAGOALVESDASILVAA/meli-item-detail-api.git
cd meli-item-detail-api
mvn clean install
```

### Run the application

```bash
mvn spring-boot:run -pl rest-adapter
```

### Verify

```bash
echo "Health:"; curl http://localhost:8080/actuator/health
echo "Product:"; curl http://localhost:8080/products/MLB123456789
```

---

## 🔗 Main Endpoints

- **Products**: `GET /products/{id}` — details, images, reviews, variations, promotions, specs, related
- **Brands**: `GET /brands/{brand}/products` — products by brand
- **Cart**: `POST /cart/{id}/items` — add to cart
- **Payments**: `GET /products/{id}/payment-options` — payment methods
- **Health**: `GET /actuator/health` — application status

---

## 📊 Coverage by Module

| Module                     | Coverage | Tests  | Status |
| -------------------------- | -------- | ------ | ------ |
| Core (Use Cases)           | 89%      | 25     | ✅      |
| App (Services)             | 100%     | 40     | ✅      |
| Rest-Adapter (Controllers) | 85%      | 23     | ✅      |
| **Total**                  | **93%**  | **88** | ✅      |

> *Note: Excludes models, DTOs, and infrastructure adapters from coverage calculation*

---

## 🔧 Test Commands

```bash
# Run all tests (93 passing, excluding models/DTOs)
mvn test

# Run tests by module
mvn test -pl core
mvn test -pl app
mvn test -pl rest-adapter

# View HTML report
open rest-adapter/target/site/jacoco/index.html
```

---

## 📊 Status

- ✅ **Architecture**: Hexagonal + multi‑module Maven
- ✅ **Framework**: Spring Boot 3.2.0 + Java 17
- ✅ **Tests**: 93 unit/integration tests (business logic focus)
- ✅ **Coverage**: 93% (goal: 80%)
- ✅ **Error handling**: MercadoLibre format
- ✅ **Docker**: Production-ready
- ✅ **Kubernetes**: Helm charts available

---

## 🛠️ Development

### 📋 Prerequisites

- Java 17+
- Maven 3.8+
- Docker (optional)

### 🔄 Dev Workflow

```bash
# 1. Local dev
mvn clean compile
mvn test

# 2. Build
todo: mvn clean install

# 3. Docker

docker build -t meli-item-detail-api .
docker run -p 8080:8080 meli-item-detail-api
```

### 🎯 Quality Goals

- **Minimum coverage**: 80% (current: 93%)
- **Test pass rate**: 100%
- **Error rate**: < 1%
- **95% requests** < 2s
- ✅ **Monitoring**: health checks & metrics
- ✅ **Documentation**: runbooks & ops guides

---

**This project is production-ready with full emergency and operations documentation.**

