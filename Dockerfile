# Set the base image to adoptopenjdk (OpenJDK) with a specific version
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file into the container at /app
COPY build/libs/dukkan-0.0.1-SNAPSHOT.jar /app

# Specify the command to run on container startup
CMD ["java", "-jar", "dukkan-0.0.1-SNAPSHOT.jar"]
