FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install openjdk-17-jdk -y
COPY . .
WORKDIR /app
FROM openjdk:17-jdk-slim

EXPOSE 8080

COPY --from=build /build/libs/Swagger-0.0.1-SNAPSHOT.jar /app/Swagger.jar

ENTRYPOINT ["java", "-jar", "app.jar"]