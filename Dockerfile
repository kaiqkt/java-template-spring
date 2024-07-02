FROM openjdk:17

MAINTAINER https://github.com/kaiqkt

COPY ./build/libs/*.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]
