FROM flowcommerce/play:0.0.5-dev

ADD . /opt/play

WORKDIR /opt/play

RUN sbt clean stage
  
RUN rm -rf /root/.ivy2
RUN rm -rf /root/.sbt

ENTRYPOINT ["java", "-jar", "/root/environment-provider.jar", "play", "splashpage", "api/target/universal/stage/bin/splashpage-api"]
