FROM frolvlad/alpine-java:jdk8-slim

# Create app directory on container
RUN mkdir -p /usr/opt/service

# Use it as the working dir
WORKDIR /usr/opt/service

# copy app jar
COPY target/*-SNAPSHOT.jar simple-client.jar

ENTRYPOINT exec java -Dserver.servlet.context-path=/$GROUP -jar simple-client.jar
