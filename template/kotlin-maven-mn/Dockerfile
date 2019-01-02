FROM maven:3.6.0-jdk-8-alpine as builder

WORKDIR /home/fn
COPY function /home/fn/
RUN mvn clean package

FROM openjdk:8u121-jdk-alpine as ship
RUN apk --no-cache add curl \
    && echo "Pulling watchdog binary from Github." \
    && curl -sSL https://github.com/openfaas-incubator/of-watchdog/releases/download/0.4.6/of-watchdog > /usr/bin/fwatchdog \
    && chmod +x /usr/bin/fwatchdog \
    && apk del curl

RUN addgroup -S app && adduser -S -g app app
WORKDIR /home/fn
COPY --from=builder /home/fn/target/app.jar ./app.jar

RUN chown -R app /home/fn
USER app

ENV MICRONAUT_SERVER_PORT=8081
ENV upstream_url="http://127.0.0.1:8081"
ENV mode="http"

ENV fprocess="java -jar app.jar"
EXPOSE 8080

HEALTHCHECK --interval=2s CMD [ -e /tmp/.lock ] || exit 1

CMD ["fwatchdog"]