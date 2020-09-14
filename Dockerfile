FROM java:8
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar -Xms2G -Xmx2G","/app.jar"]