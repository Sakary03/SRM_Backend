# Use Eclipse Temurin JDK 17 as the base image
FROM eclipse-temurin:17-jdk-focal

# Set the working directory in the container
WORKDIR /app

# Copy Maven wrapper and project descriptor files
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Download dependencies
RUN ./mvnw dependency:go-offline

# Copy application source code
COPY src ./src

# Expose the default Spring Boot port
EXPOSE 8080

# Default command to run the application
CMD ["./mvnw", "spring-boot:run"]
