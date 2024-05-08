# Use the official OpenJDK image from Docker Hub as the base image
FROM openjdk:11-jdk-slim

# Set the port number that needs to be exposed
EXPOSE 8080

# Set environment variables passed into the Docker container
ENV PORT 8080
ENV DB_USERNAME=postgres
ENV DB_PASSWORD=postgres
ENV DB_PORT=5432
ENV DB_HOST=localhost
ENV DB_NAME=postgres
ENV ENV=default

# Add a volume pointing to /tmp
VOLUME /tmp

# Copy the jar file into the image
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

# Run the application
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]
