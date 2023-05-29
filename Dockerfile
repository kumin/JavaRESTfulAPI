FROM gradle:8.1.1-jdk17-alpine as builder

WORKDIR /app

COPY --chown=gradle:gradle /app/src  ./src
COPY --chown=gradle:gradle /app/build.gradle ./

RUN gradle build

FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY --from=builder /app/build/libs/*.jar ./

ENTRYPOINT ["java", "-jar", "/app/app.jar"]