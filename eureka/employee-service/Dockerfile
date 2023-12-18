FROM openjdk:8-jdk-alpine

ARG JAR_FILE=target/employee-service-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} employee-service-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","/employee-service-0.0.1-SNAPSHOT.jar"]