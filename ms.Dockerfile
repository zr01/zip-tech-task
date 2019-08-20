FROM openjdk:8-alpine

ENV APP_NAME 'zip-tech-task'
ENV APP_HOME '/opt/${APP_NAME}'
ENV JAVA_ARGS ''

RUN mkdir -p ${APP_HOME}
COPY target/${APP_NAME}.jar ${APP_HOME}
WORKDIR ${APP_HOME}

EXPOSE 8080

ENTRYPOINT [ "sh", "-c" ]
CMD ["exec java -jar ${JAVA_ARGS} ${APP_NAME}.jar"]
