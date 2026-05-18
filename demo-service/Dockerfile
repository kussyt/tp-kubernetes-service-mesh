# Cas 1 & 2 — image à partir du fat-JAR déjà compilé en local (mvn package)
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY target/demo-service-*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
