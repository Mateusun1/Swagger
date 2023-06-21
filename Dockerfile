FROM openjdk

WORKDIR /app

COPY build/libs/Swagger-0.0.1-SNAPSHOT.jar /app/Swagger.jar

ENTRYPOINT ["java", "-jar", "Swagger.jar"]