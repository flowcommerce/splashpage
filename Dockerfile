FROM flowcommerce/play:0.0.4

ADD . /opt/play

WORKDIR /opt/play

RUN sbt clean stage
  
ENTRYPOINT ["java", "-jar", "environment-provider.jar", "play", "splashpage", "api/target/universal/stage/bin/splashpage-api"]
