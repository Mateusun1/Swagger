# Estágio de compilação
FROM gradle:7.6.1-jdk17 AS build
WORKDIR /app
COPY . .
RUN gradle clean build

# Estágio de produção
FROM openjdk:17-jdk-slim
COPY --from=build build/libs/Swagger-1.jar /app/swagger.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/usr/local/lib/swagger.jar"]
