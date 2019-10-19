FROM frolvlad/alpine-java:jdk8-slim

# Create app directory on container
RUN mkdir -p /usr/opt/service

# Use it as the working dir
WORKDIR /usr/opt/service

# copy app jar
COPY target/*-SNAPSHOT.jar heavy-stress.jar

ENTRYPOINT exec java -jar heavy-stress.jar
