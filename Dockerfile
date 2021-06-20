FROM gradle:5.3.0-jdk-alpine AS base
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY build.gradle settings.gradle $APP_HOME
  
COPY gradle $APP_HOME/gradle
COPY --chown=gradle:gradle . /home/gradle/src
USER root
RUN chown -R gradle /home/gradle/src
    

RUN ./gradlew build || return 0
COPY . .  
RUN ./gradlew clean build
RUN	./gradlew test || return 0

FROM adoptopenjdk/openjdk11:alpine-jre
ENV ARTIFACT_NAME=exchange-0.0.1-SNAPSHOT.jar
ENV APP_HOME=/usr/app/
    
WORKDIR $APP_HOME
COPY --from=base $APP_HOME/build/libs/$ARTIFACT_NAME .
    
EXPOSE 8080
ENTRYPOINT exec java -jar ${ARTIFACT_NAME}