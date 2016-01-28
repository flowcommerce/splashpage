FROM fbe979e12570

ADD . /opt/play

WORKDIR /opt/play

RUN sbt -Dsbt.ivy.home=.ivy2 clean stage

CMD nohup /opt/play/api/target/universal/stage/bin/splashpage-api
