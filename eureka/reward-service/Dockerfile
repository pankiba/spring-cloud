FROM openjdk:8-jdk-alpine

ARG JAR_FILE=target/reward-service-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} reward-service-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","/reward-service-0.0.1-SNAPSHOT.jar"]