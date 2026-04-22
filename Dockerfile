# Stage 1: build the application
FROM maven:3.9.9-eclipse-temurin-21 AS builder

WORKDIR /app

COPY pom.xml .
COPY src ./src
COPY .mvn ./.mvn
COPY mvnw .
COPY mvnw.cmd .

RUN ./mvnw clean package -DskipTests

# Stage 2: run the application
FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=builder /app/target/blackjack-api-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]