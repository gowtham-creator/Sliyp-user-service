# Use the OpenJDK 11 Alpine-based image as the base image
FROM openjdk:11-jre-slim

# Set environment variables for Maven installation
ENV MAVEN_HOME=/usr/share/maven
ENV MAVEN_VERSION=3.8.4
ENV PATH=$MAVEN_HOME/bin:$PATH

# Install required packages and dependencies
RUN apk add --no-cache curl tar && mkdir -p /usr/share/maven && curl -fsSL https://archive.apache.org/dist/maven/maven-3/$MAVEN_VERSION/binaries/apache-maven-$MAVEN_VERSION-bin.tar.gz | tar -xzC $MAVEN_HOME --strip-components=1 && apk del curl tar && rm -rf /var/cache/apk/*

# Set the working directory inside the container
WORKDIR /app



RUN mvn clean install

# Copy the JAR file from your host into the container at /app
COPY target/user-service-0.0.1-SNAPSHOT.jar .

# Expose the port that your Spring Boot application listens on
EXPOSE 8080

# Define the command to run your application
CMD ["java", "-jar", "user-service-0.0.1-SNAPSHOT.jar"]
