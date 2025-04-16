# Spring Boot Product API with In-Memory Cache

A simple RESTful API built with Spring Boot to manage products using an in-memory data store.  
Includes full CI/CD with GitHub Actions and Docker support.

## Features
- CRUD operations for products
- In-memory cache 
- RESTful API with Spring Boot
- OpenAPI documentation
- Unit and integration tests
- Dockerfile for containerization
- GitHub Actions for CI/CD

## Technologies
- Java 17+
- Spring Boot
- Lombok
- MapStruct
- Swagger / OpenAPI
- Docker
- GitHub Actions
- Jacoco (test coverage)

## How to Run

```bash
# Build the app
mvn clean install

# Run locally
mvn spring-boot:run
