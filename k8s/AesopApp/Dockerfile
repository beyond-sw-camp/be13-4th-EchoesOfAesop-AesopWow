FROM nginx:latest
LABEL maintainer="hong123 <hong123@gmail.com>"
LABEL version="1.0"
COPY dist /usr/share/nginx/html
COPY default.conf /etc/nginx/conf.d/default.conf
ENV TZ=ASIA/Seoul
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]