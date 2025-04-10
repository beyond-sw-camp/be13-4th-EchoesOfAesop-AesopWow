FROM eclipse-temurin:21-jre-alpine

LABEL maintainer="hong123 <hong123@gmail.com>"
LABEL version="1.0"

COPY ./build/libs/be13-2nd-AesopWow-EchoesOfAesop-0.0.1-SNAPSHOT.jar /root

ARG BUILD_PROFILE=dev
ENV APP_PROFILE=${BUILD_PROFILE}

# 포트 설정
ARG BUILD_PORT=8091
ENV TZ=Asia/Seoul
EXPOSE ${BUILD_PORT}

# 작업 디렉토리
WORKDIR /root

# 실행 명령
CMD [ "java", "-jar", "be13-2nd-AesopWow-EchoesOfAesop-0.0.1-SNAPSHOT.jar", "--spring.profiles.active=${APP_PROFILE}" ]
