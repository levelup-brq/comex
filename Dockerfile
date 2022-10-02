FROM eclipse-temurin:11-jdk
RUN adduser --system --group spring
USER spring:spring
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-Xmx512m","-Dserver.port=${PORT}","-jar","/app.jar"]
