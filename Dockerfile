FROM flowdocker/play:0.0.37

ADD . /opt/play

WORKDIR /opt/play

RUN sbt clean stage

WORKDIR api/target/universal/stage

ENTRYPOINT ["java", "-jar", "/root/environment-provider.jar", "--service", "play", "splashpage", "bin/splashpage-api"]
