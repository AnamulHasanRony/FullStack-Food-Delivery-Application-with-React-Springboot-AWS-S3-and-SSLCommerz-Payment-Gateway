
#FROM eclipse-temurin:21-jdk-jammy AS builder
FROM eclipse-temurin:21-jdk-alpine AS builder

WORKDIR /build

COPY pom.xml .
COPY .mvn/ .mvn/
COPY mvnw .

RUN ./mvnw dependency:go-offline -B

COPY src ./src
RUN ./mvnw clean package -DskipTests

# =============================================================================
# Stage 2: Distroless runtime (your part – very good!)
# =============================================================================
FROM gcr.io/distroless/java21-debian12

WORKDIR /app

# Copy only the final JAR
COPY --from=builder /build/target/*.jar /app.jar

# Spring Boot default port
#EXPOSE 8080
EXPOSE ${PORT:-8080}

#ENTRYPOINT ["java", \
#    "-Xms256m", \
#    "-Xmx512m", \
#    "-XX:+UseZGC", \
#    "-Dserver.port=${PORT}", \
#    "-jar", \
#    "/app.jar"]

#for render jvm optimization

ENTRYPOINT ["java", \
    "-XX:+UseContainerSupport", \
    "-XX:MaxRAMPercentage=60.0", \
    "-XX:InitialRAMPercentage=20.0", \
    "-Dserver.port=${PORT}", \
    "-jar", \
    "/app.jar"]



#FROM maven as build
#WORKDIR /app
#COPY . .
#RUN mvn install
#
#FROM eclipse-temurin:21-jre-jammy
#WORKDIR /app
#COPY --from=build /app/target/food-delivery-app-backend.jar /app/food-delivery-app-backend.jar
#EXPOSE 8080
#ENTRYPOINT ["java", "-jar", "food-delivery-app-backend.jar"]
#
