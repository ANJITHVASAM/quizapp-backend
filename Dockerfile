# Stage 1: Build the application
FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app

# Copy only pom.xml first to leverage Docker caching for dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy source and build the JAR
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Create the final lightweight image
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copy the JAR from the build stage
COPY --from=build /app/target/quizapp-0.0.1-SNAPSHOT.jar .

EXPOSE 8001

# Note: Added the space after ENTRYPOINT to fix your previous error
ENTRYPOINT ["java", "-jar", "/app/quizapp-0.0.1-SNAPSHOT.jar"]