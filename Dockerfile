FROM java:8
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["/bin/bash","-c","java -jar -Xms3072m -Xmx3072m /app.jar"]