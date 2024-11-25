FROM openjdk:17-jdk-slim
WORKDIR /app
RUN apt-get update && apt-get install -y maven
COPY . .
RUN mvn clean package -DskipTests
ENTRYPOINT ["java", "-jar", "/app/target/astrosales-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080
