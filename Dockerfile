#Build
FROM maven:3.9.4-eclipse-temurin-17 AS builder
WORKDIR /api
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

#Run
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /api
COPY --from=builder /api/target/api-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
