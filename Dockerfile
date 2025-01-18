FROM eclipse-temurin:21-jre-alpine
COPY build/libs/minify-0.0.1-SNAPSHOT.jar minify.jar
ENTRYPOINT ["java","-jar","/minify.jar"]