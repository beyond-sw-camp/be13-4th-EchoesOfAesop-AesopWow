FROM eclipse-temurin:21-jre-alpine

LABEL maintainer="hong123 <hong123@gmail.com>"
LABEL version="1.0"

COPY be13-2nd-AesopWow-EchoesOfAesop-0.0.1-SNAPSHOT.jar /app.jar



ARG BUILD_PROFILE=dev
ENV APP_PROFILE=${BUILD_PROFILE}

ARG BUILD_PORT=8091
ENV TZ=Asia/Seoul
EXPOSE ${BUILD_PORT}

WORKDIR /root

ENTRYPOINT ["java", "-jar", "/app.jar", "--spring.profiles.active=${APP_PROFILE}"]