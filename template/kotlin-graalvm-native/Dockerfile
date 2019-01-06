FROM gradle:5.1.0-jdk8-alpine as builder
USER root
COPY function /home/fn/
WORKDIR /home/fn
RUN gradle build --no-daemon --console plain

FROM oracle/graalvm-ce:1.0.0-rc10 as native
WORKDIR /home/fn
COPY --from=builder /home/fn/build/libs/fn.jar ./fn.jar
RUN native-image -jar fn.jar --static

FROM alpine:3.8 as ship
RUN apk --no-cache add curl \
    && echo "Pulling watchdog binary from Github." \
    && curl -sSL https://github.com/openfaas/faas/releases/download/0.9.14/fwatchdog > /usr/bin/fwatchdog \
    && chmod +x /usr/bin/fwatchdog \
    && apk del curl \
    && rm -rf /var/cache/apk/*

RUN addgroup -S fn && adduser -S -g fn fn
WORKDIR /home/fn
COPY --from=native /home/fn/fn ./fn

RUN chown -R fn /home/fn
USER fn

ENV fprocess="./fn"
EXPOSE 8080

HEALTHCHECK --interval=2s CMD [ -e /tmp/.lock ] || exit 1

CMD ["fwatchdog"]
