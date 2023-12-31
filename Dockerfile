FROM eclipse-temurin:17-jdk-alpine as build

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} app.jar

EXPOSE 80

ENTRYPOINT ["java","-jar","/app.jar"]

