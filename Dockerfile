FROM flowcommerce/play:0.0.0

ADD . /opt/play

WORKDIR /opt/play

RUN sbt -Dsbt.ivy.home=.ivy2 clean stage

CMD nohup /opt/play/api/target/universal/stage/bin/splashpage-api
