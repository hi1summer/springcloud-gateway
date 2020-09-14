FROM java:8
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["/bin/bash","-c","java -Xms2048m -Xmx2048m -jar /app.jar"]