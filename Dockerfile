FROM maven:3.9.4-eclipse-temurin-21 AS build


# Set the working directory
WORKDIR /app

COPY CarRental/pom.xml .


COPY CarRental/src ./src

RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY --from=build /app/target/CarRental-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]
