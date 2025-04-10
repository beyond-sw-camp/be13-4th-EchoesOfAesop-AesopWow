FROM eclipse-temurin:21-jre-alpine
LABEL maintainer="hong123 <hong123@gmail.com>"
LABEL version="1.0"
COPY /build/libs/be13-2nd-AesopWow-EchoesOfAesop-0.0.1-SNAPSHOT.jar /
ARG BUILD_PORT=8091
ENV TZ=Asia/Seoul
EXPOSE ${BUILD_PORT}
WORKDIR /root
CMD [ "java", "-jar", "be13-2nd-AesopWow-EchoesOfAesop-0.0.1-SNAPSHOT.jar" ]