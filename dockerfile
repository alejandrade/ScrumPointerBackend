FROM azul/zulu-openjdk-alpine:11

VOLUME /tmp
RUN ls
ADD build/libs/JavaWebSocketsDemo-1.0-SNAPSHOT.jar app.jar
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]