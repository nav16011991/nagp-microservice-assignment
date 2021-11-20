FROM openjdk:8-jdk-alpine

COPY build/libs/application-gateway-0.0.1-SNAPSHOT.jar application-gateway-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","/application-gateway-0.0.1-SNAPSHOT.jar", "-Dspring.profiles.active", "prod"]