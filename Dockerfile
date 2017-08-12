FROM ubuntu:latest

RUN apt update
RUN apt install openjdk-8-jdk bash -y
[ -e which curl ] && apt install curl

ENV THRIFT_VERSION 0.10.0
RUN curl -sSl http://archive.apache.org/dist/thrift/${THRIFT_VERSION}/thrift-${THRIFT_VERSION}.tar.gz \
  | tar xzf - \
  && cd thrift-${THRIFT_VERSION} \
  && ./configure --with-java --without-erlang --without-php \
  && make \
  && make install

# Check COPY and ADD
RUN mkdir -p /git/src/main && mkdir -p /git/scripts && mkdir -p /git/stats
COPY /src/ /git/src/
COPY /scripts/ /git/scripts/

#RUN /git/scripts/stats_algs.sh 
RUN /bin/bash -c  "source /git/scripts/activate"