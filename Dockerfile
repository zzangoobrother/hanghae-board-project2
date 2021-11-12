FROM openjdk:8-jdk

VOLUME /tmp

ARG JAR_FILE=./build/libs/hanghae-board-project2-1.0.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]