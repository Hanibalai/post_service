FROM openjdk:17-jdk-slim-buster

COPY post_service-0.0.1-SNAPSHOT.war /opt/app.war
#COPY db/script.sql /opt/docker-entrypoint-initdb.d

EXPOSE 8080
ENTRYPOINT java -jar /opt/app.war