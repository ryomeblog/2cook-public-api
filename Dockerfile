FROM maven:3.8.6-openjdk-8 AS builder
WORKDIR /tmp
COPY ./src ./src
COPY ./pom.xml .
RUN mvn package
FROM openjdk:8u102-jdk
COPY --from=builder /tmp/target/2cook-dev-0.0.1.jar /app/2cook-dev-0.0.1.jar
EXPOSE 5000
ENTRYPOINT ["java", "-jar", "/app/2cook-dev-0.0.1.jar"]