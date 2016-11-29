FROM apacheignite/ignite
COPY sbt /bin/
RUN chmod +x /bin/sbt
COPY sbt-launch.jar /bin/
RUN sbt
WORKDIR /opt/ignite
