FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD target/app.jar app.jar
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
