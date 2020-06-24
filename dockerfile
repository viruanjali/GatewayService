FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/GatewayService-0.0.1-SNAPSHOT.jar GatewayService.jar
ENTRYPOINT ["java", "-jar", "GatewayService.jar"]