# Use a base image with Java (e.g., OpenJDK)
FROM adoptopenjdk/openjdk11:alpine-jre

# Set the working directory inside the container
WORKDIR /app

RUN mvn clean install

# Copy the JAR file from your host into the container at /app
COPY target/user-service-0.0.1-SNAPSHOT.jar .

# Expose the port that your Spring Boot application listens on
EXPOSE 8080

# Define the command to run your application
CMD ["java", "-jar", "user-service-0.0.1-SNAPSHOT.jar"]
