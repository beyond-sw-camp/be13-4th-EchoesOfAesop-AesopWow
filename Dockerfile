FROM eclipse-temurin:21-jre-alpine

LABEL maintainer="hong123 <hong123@gmail.com>"
LABEL version="1.0"

# 👉 JAR 파일 복사 (추가)
COPY ./target/be13-2nd-AesopWow-EchoesOfAesop-0.0.1-SNAPSHOT.jar /root

# 👉 프로파일 관련 ARG/ENV 추가
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
