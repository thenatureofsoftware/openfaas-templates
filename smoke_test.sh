#!/bin/bash

trap "exit 1" TERM
export TOP_PID=$$

function check() {
    if [ ! $? -eq 0 ]; then
        echo "build for $template FAILED"
        clean_and_kill
    fi
}

function check_status() {
    if [ ! $http_status -eq 200 ]; then
        echo "INFO build for $template FAILED"
        clean_and_kill
    fi
}

function clean_and_kill() {
    if [ ! -z "$container" ]; then
        docker rm -f $container
    fi
    kill -s TERM $TOP_PID
}

function check_template_name() {
    template_name=$(cat $template/template.yml | grep language)
    if [ ! "$template_name" = "language: $template" ]; then
        echo "ERROR invalid template language"
        echo "INFO build for $template FAILED"
        clean_and_kill
    fi
}

cd template
dirs=(*)
for template in "${dirs[@]}"; do
  echo "buidling $template"
  check_template_name
  img="tnos/$template:latest"
  docker build --quiet --no-cache -t $img $template > /dev/null 2>&1
  check
  container=$(docker run -d -p 8888:8080 $img)
  check
  sleep 10
  http_status=$(curl -X POST -w "%{http_code}" -d "test" -H "Content-Type: text/plain" -o /dev/null -s http://localhost:8888)
  check
  check_status
  docker rm -f $container > /dev/null 2>&1
  docker rmi -f $img > /dev/null 2>&1
  echo "INFO build for $template OK"
done