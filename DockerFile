# Etapa 1 — build da aplicação
FROM ubuntu:latest AS build

RUN apt-get update -y
RUN apt-get install -y openjdk-17-jdk maven

WORKDIR /app
COPY . .

RUN mvn clean install -DskipTests


FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=build /app/target/todolist-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]