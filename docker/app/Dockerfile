FROM openjdk:16-alpine3.13

RUN mkdir /opt/app

COPY ./RedCollar1-0.0.1-SNAPSHOT.jar /opt/app

EXPOSE 8080

CMD ["java", "-jar", "/opt/app/RedCollar1-0.0.1-SNAPSHOT.jar"]