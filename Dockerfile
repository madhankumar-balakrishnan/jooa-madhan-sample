FROM java:8
VOLUME /tmp
ADD jooq-madhan-sample-0.0.1-SNAPSHOT.jar jooq-madhan.jar
RUN bash -c 'touch /jooq-madhan.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/jooq-madhan.jar"]