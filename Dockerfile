#FROM openjdk:16-jdk-alpine3.12
#COPY ./target/chaostoolkit-springboot-demo-0.0.1-SNAPSHOT.jar chaostoolkit-springboot-demo-0.0.1-SNAPSHOT.jar
#EXPOSE 8080
#ENTRYPOINT ["java", "-jar", "chaostoolkit-springboot-demo-0.0.1-SNAPSHOT.jar"]
FROM openjdk:16-jdk-alpine3.12
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]