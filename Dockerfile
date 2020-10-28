FROM openjdk:8-jre-alpine

WORKDIR /

COPY target/ejemplo-pruebas-0.0.1-SNAPSHOT.jar /

EXPOSE 9090

CMD ["java", "-jar", "/ejemplo-pruebas-0.0.1-SNAPSHOT.jar"]

