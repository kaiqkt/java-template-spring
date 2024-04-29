FROM openjdk:17

MAINTAINER https://github.com/kaiqkt

COPY ./build/libs/*.jar template-api.jar

ENTRYPOINT ["java","-jar","template-api.jar"]

ENV PROFILE local
