# FROM lpicanco/java11-alpine:latest
FROM sczedge/img_librarian:2.2

ENV KAFKA_HOME /opt/kafka_2.12-2.8.0

RUN apk update
RUN apk add --no-cache bash

WORKDIR /opt/kafka_2.12-2.8.0/

ENTRYPOINT ["bin/kafka-server-start.sh","config/server.properties"]