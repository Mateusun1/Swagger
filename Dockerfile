# Estágio de compilação
FROM gradle:7.6.1-jdk17 AS build
WORKDIR /home/app
COPY . .
RUN gradle clean build

# Estágio de produção
FROM adoptopenjdk:17-jre-hotspot
COPY --from=build /home/app/backend/build/libs/Swagger-1.jar /usr/local/lib/swagger.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/usr/local/lib/swagger.jar"]
