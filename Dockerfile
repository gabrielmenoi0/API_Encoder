FROM openjdk:11

WORKDIR /app

COPY target/API-Encoder-0.0.1-SNAPSHOT.jar .
EXPOSE 8081

CMD ["java", "-jar", "API-Encoder-0.0.1-SNAPSHOT.jar"]