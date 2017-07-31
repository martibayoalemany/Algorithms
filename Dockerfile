FROM ubuntu:latest

RUN apt update
RUN apt install openjdk-8-jdk bash -y
RUN mkdir -p /git/src/main && mkdir -p /git/scripts && mkdir -p /git/stats
ADD src /git/
ADD scripts /git/
#RUN /git/scripts/stats_algs.sh 
RUN /bin/bash -c  "source /git/scripts/activate"