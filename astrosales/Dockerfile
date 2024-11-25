FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/astrosales-0.0.1-SNAPSHOT.jar astrosales-api.jar
ENTRYPOINT ["java", "-jar", "astrosales-api.jar"]