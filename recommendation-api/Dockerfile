FROM maven:3.8.2-openjdk-17 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

FROM openjdk:17-alpine
COPY --from=build /home/app/target/recommendation-api-0.0.1-SNAPSHOT.jar /usr/app/recommendation-api.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/app/recommendation-api.jar"]