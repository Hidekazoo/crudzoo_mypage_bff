FROM gcr.io/distroless/java17-debian11

WORKDIR /
COPY target/uberjar/crudzoo_mypage_bff-0.1.0-SNAPSHOT-standalone.jar app.jar
EXPOSE 8080

CMD ["app.jar"]