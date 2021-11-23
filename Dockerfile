FROM openjdk:11-jdk

COPY ./build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]