FROM adoptopenjdk:11.0.9_11-jre-openj9-0.23.0
COPY target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
