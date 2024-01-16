FROM openjdk:17 as build
WORKDIR /app
COPY target/To-Do-list-0.0.1-SNAPSHOT.jar /app/app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]