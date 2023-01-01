docker run -it --rm \
  -p 4000:8080 \
  --name wiremock \
  -v $PWD/stub:/home/wiremock \
  wiremock/wiremock:2.35.0