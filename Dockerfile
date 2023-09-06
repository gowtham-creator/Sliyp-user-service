# Stage 1: Build the Java application using Maven image
FROM maven:3.8.4-openjdk-11 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src src
RUN mvn clean package -DskipTests

# Stage 2: Create a lightweight image for running the application
FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=build /app/target/user-service-0.0.1-SNAPSHOT.jar .
CMD ["java", "-jar", "user-service-0.0.1-SNAPSHOT.jar"]

