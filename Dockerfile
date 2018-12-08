FROM frolvlad/alpine-oraclejdk8:slim

MAINTAINER Acris Liu "acrisliu@gmail.com"

COPY docker-entrypoint.sh /usr/local/bin/

ENV TIMEZONE="Asia/Shanghai"
# Set Timezone
RUN set -ex \
    && sed -i 's/dl-cdn.alpinelinux.org/mirrors.ustc.edu.cn/g' /etc/apk/repositories \
    && apk add --no-cache --virtual .build-deps tzdata \
    && cp /usr/share/zoneinfo/${TIMEZONE} /etc/localtime \
    && echo "${TIMEZONE}" > /etc/timezone \
    && apk del .build-deps \
    && chmod +x /usr/local/bin/docker-entrypoint.sh

VOLUME /tmp

ARG APP_VERSION="1.0.0"
ENV APP_NAME=saas-hotel-app-${APP_VERSION}
ADD saas-hotel-app/target/$APP_NAME.jar /

ENV JAVA_OPTS=""
ENV SPRING_BOOT_OPTS=""

EXPOSE 8080

ENTRYPOINT ["docker-entrypoint.sh"]