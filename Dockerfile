FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DiskipTests

FROM openjdk:17-jdk-slim
COPY --from=build /tag/bookflow-0.0.1-SNAPSHOT.jar bookflow.jar
EXPOSE 9000
ENTRYPOINT ["java","-jar","bookflow.jar"]