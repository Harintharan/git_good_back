# Use an official Java runtime as a parent image
FROM openjdk:17-alpine



# Set the working directory
WORKDIR /app

# Add the application's jar to the container
COPY target/*.jar app.jar

# Expose port 8081
EXPOSE 8081

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]