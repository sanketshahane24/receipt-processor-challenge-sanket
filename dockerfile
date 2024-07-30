FROM maven:3.8-openjdk-17 as build

WORKDIR /app
COPY reciept-processor/pom.xml reciept-processor/pom.xml
COPY reciept-processor/src reciept-processor/src
WORKDIR /app/reciept-processor
RUN mvn clean package -DskipTests



FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY --from=build /app/reciept-processor/target/reciept-processor-0.0.1-SNAPSHOT.jar /app/app.jar

EXPOSE 8080

ENTRYPOINT [ "java","-jar", "/app/app.jar" ]