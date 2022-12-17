FROM maven AS builder
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim
ARG JAR_FILE=/target/*.jar
COPY --from=builder ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
