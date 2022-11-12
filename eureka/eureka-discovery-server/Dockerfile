FROM openjdk:8-jdk-alpine

ARG JAR_FILE=target/eureka-discovery-server-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} eureka-discovery-server-0.0.1-SNAPSHOT.jar

EXPOSE 8761

ENTRYPOINT ["java","-jar","/eureka-discovery-server-0.0.1-SNAPSHOT.jar"]