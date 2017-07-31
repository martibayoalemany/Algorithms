FROM ubuntu:latest

RUN apt update
RUN apt install openjdk-8-jdk bash -y
RUN mkdir -p /git/src && mkdir -p /git/scripts
COPY src/* /git/src
COPY scripts/* /git/scripts/
#RUN /git/scripts/stats_algs.sh 
RUN /bin/bash -c  "source /git/scripts/activate"