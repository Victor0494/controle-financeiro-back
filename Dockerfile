FROM eclipse-temurin:21-alpine

WORKDIR /app

COPY target/financeiro-0.0.1-SNAPSHOT.jar /app/financeiro-0.0.1-SNAPSHOT.jar
COPY key/ /app/key

ENTRYPOINT ["java", "-jar", "financeiro-0.0.1-SNAPSHOT.jar"]

EXPOSE 8080