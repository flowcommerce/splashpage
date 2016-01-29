FROM flowcommerce/play:0.0.6

ADD . /opt/play

WORKDIR /opt/play

RUN sbt clean stage
  
ENTRYPOINT ["java", "-jar", "/root/environment-provider.jar", "run", "play", "splashpage", "api/target/universal/stage/bin/splashpage-api"]
