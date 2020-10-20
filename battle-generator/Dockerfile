FROM openjdk:8-jdk-alpine
COPY target/battle-generator.jar app.jar
EXPOSE 8081 4001
ENTRYPOINT ["java", "-agentlib:jdwp=transport=dt_socket,address=4001,server=y,suspend=n", "-jar","/app.jar"]