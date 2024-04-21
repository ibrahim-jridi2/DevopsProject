#FROM eclipse-temurin:17-jdk-alpine
#VOLUME /tmp
#COPY target/*.jar app.jar
#EXPOSE 9098
#ENTRYPOINT ["java","-jar","app.jar"]
#
#





#FROM openjdk:1.8
#COPY target/SkiStationProject-0.0.1-SNAPSHOT.jar app.jar
#ENTRYPOINT ["java", "-jar", "/app.jar"]
# Use a base image with JDK and Maven installed
FROM maven:3.8.4-openjdk-17-slim AS build


# Copy the source code and pom.xml to the container
COPY pom.xml .
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Create a volume directory
VOLUME /data

# Use a lightweight base image for the application
FROM openjdk:17-slim
WORKDIR /app

# Copy the built JAR file from the build stage
COPY --from=build target/SkiStationProject-0.0.1-SNAPSHOT.jar SkiStationProject-0.0.1-SNAPSHOT.jar

# Expose the port your application runs on
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "SkiStationProject-0.0.1-SNAPSHOT.jar"]
