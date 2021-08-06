FROM openjdk:16-jdk-alpine3.12
COPY ./target/chaostoolkit-springboot-demo-0.0.1-SNAPSHOT.jar chaostoolkit-springboot-demo-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "chaostoolkit-springboot-demo-0.0.1-SNAPSHOT.jar"]