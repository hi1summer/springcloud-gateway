FROM java:8
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Xms2g -Xmx2g -jar","/app.jar"]