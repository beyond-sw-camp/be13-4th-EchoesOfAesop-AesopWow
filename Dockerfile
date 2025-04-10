FROM eclipse-temurin:21-jre-alpine
LABEL maintainer="your-name <your@email.com>"
LABEL version="1.0"
COPY ./target/your-backend.jar /root
ARG BUILD_PROFILE=dev
ARG BUILD_PORT=8080
ENV TZ=Asia/Seoul
ENV APP_PROFILE=${BUILD_PROFILE}
EXPOSE ${BUILD_PORT}
WORKDIR /root
CMD [ "java", "-jar", "your-backend.jar", "--spring.profiles.active=${APP_PROFILE}" ]