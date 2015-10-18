FROM giltarchitecture/ubuntu-jvm:0.6

MAINTAINER mbryzek@alum.mit.edu

ADD . /usr/share/splashpage

WORKDIR /usr/share/splashpage

RUN sbt -Dsbt.ivy.home=.ivy2 clean stage

RUN ln -s /usr/share/splashpage/api/target/universal/stage /usr/share/splashpage-api
RUN ln -s /usr/share/splashpage/www/target/universal/stage /usr/share/splashpage-www
