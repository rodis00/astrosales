FROM maven:3.8.8-eclipse-temurin-17 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /target/astrosales-0.0.1-SNAPSHOT.jar astrosales-api.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "astrosales-api.jar"]