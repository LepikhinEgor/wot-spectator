FROM openjdk:8-jdk-alpine
COPY target/battle-spy.jar app.jar
EXPOSE 8082 4002
ENTRYPOINT ["java", "-agentlib:jdwp=transport=dt_socket,address=4002,server=y,suspend=n", "-jar","/app.jar"]