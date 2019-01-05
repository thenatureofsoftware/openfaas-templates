FROM maven:3.6.0-jdk-8-alpine as builder

WORKDIR /home/fn
COPY function /home/fn/
RUN mvn clean package

FROM openjdk:8-jre-alpine as ship
RUN apk --no-cache add curl \
    && echo "Pulling watchdog binary from Github." \
    && curl -sSL https://github.com/openfaas-incubator/of-watchdog/releases/download/0.4.6/of-watchdog > /usr/bin/fwatchdog \
    && chmod +x /usr/bin/fwatchdog \
    && apk del curl \
    && rm -rf /var/cache/apk/*

RUN addgroup -S fn && adduser -S -g fn fn
WORKDIR /home/fn
COPY --from=builder /home/fn/target/fn.jar ./fn.jar

RUN chown -R fn /home/fn
USER fn

ENV upstream_url="http://127.0.0.1:8081"
ENV mode="http"

ENV fprocess="java -noverify -Dserver.port=8081 -jar fn.jar"
EXPOSE 8080

HEALTHCHECK --interval=2s CMD [ -e /tmp/.lock ] || exit 1

CMD ["fwatchdog"]
