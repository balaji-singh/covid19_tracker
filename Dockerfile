FROM openjdk:11-jdk-slim
VOLUME /tmp
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
ADD /target/covid19_tracker-0.0.1-SNAPSHOT.jar /tmp/covid19_tracker.jar
EXPOSE 80

ENTRYPOINT exec java -Djava.security.egd=file:/dev/./urandom -jar covid19_tracker.jar
# For Spring-Boot project, use the entrypoint below to reduce Tomcat startup time.
#ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar employee-time-tracker.jar