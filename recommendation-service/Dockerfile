FROM amazoncorretto:17-alpine-jdk

WORKDIR /app

COPY target/*.jar /app/app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]