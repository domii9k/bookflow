FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install openjdk-17-jdk -y
COPY . .

RUN apt-get install maven -y
RUN mvn clean install -DskipTests

FROM openjdk:17-jdk-slim

EXPOSE 9000

COPY --from=build /target/bookflow-0.0.1-SNAPSHOT.jar bookflow.jar

ENTRYPOINT [ "java", "-jar", "bookflow.jar" ]

## eu modifiquei este arquivo