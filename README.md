# 🚀 Spring Boot Product API with In-Memory Cache

A simple RESTful API built with Spring Boot to manage products using an in-memory data store.  
Includes full CI/CD using GitHub Actions, Docker support, and test coverage reporting with JaCoCo.

---

## 🧩 Features

- ✅ CRUD operations for products
- 🧠 In-memory cache with `ConcurrentHashMap`
- 🔗 RESTful API using Spring Boot
- 🐳 Docker container support
- 📖 Swagger/OpenAPI documentation
- 🧪 Unit and integration testing
- ⚙️ GitHub Actions for CI/CD
- 📊 Code coverage with JaCoCo

---

## 🛠️ Technologies Used

- Java 17.0.12
- Spring Boot 3.4.4
- Lombok
- MapStruct 1.5.3
- Swagger / OpenAPI (Springdoc)
- Docker
- GitHub Actions
- JaCoCo (code coverage)

---

## ▶️ How to Run Locally

```bash
# 1. Build the application
mvn clean install

# 2. Run the application
mvn spring-boot:run

# Run all tests
mvn test

# Generate code coverage report
mvn jacoco:report

# Open coverage report in your browser (for Mac/Linux)
open target/site/jacoco/index.html

# Build Docker image
docker build -t nishikapanchal28/api .

# Run Docker container
docker run -p 8080:8080 nishikapanchal28/api

http://localhost:8080/swagger-ui/index.html

Nishika Panchal

🔗 GitHub: nishikapanchal28

🐳 DockerHub: nishikapanchal28