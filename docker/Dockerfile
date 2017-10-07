FROM java:8
VOLUME /tmp
ADD oteo-rest-api-0.0.1.jar app.jar
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS=""

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=8081 -jar /app.jar" ]