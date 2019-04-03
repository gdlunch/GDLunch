FROM openjdk:11-jre-slim

MAINTAINER labuda.dev

COPY web/build/libs/web-1.0-SNAPSHOT.jar gdlunch.jar
COPY restaurants.json restaurants.json
COPY apiKeys.properties apiKeys.properties

EXPOSE 8080

ENTRYPOINT java -jar gdlunch.jar
