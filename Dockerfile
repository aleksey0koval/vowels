FROM maven:3.8.6-openjdk-11-slim AS build

ENV WORKDIR=/opt/vowels

WORKDIR ${WORKDIR}

COPY pom.xml .

RUN mvn clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r target/

COPY src ./src

RUN mvn clean package

FROM openjdk:11-jre-slim

ENV WORKDIR=/opt/vowels
ENV JAR_NAME=vowels-0.0.1-SNAPSHOT.jar

WORKDIR ${WORKDIR}

RUN mkdir -p /opt/vowels

COPY --from=build $WORKDIR/target/classes/data/input.txt input.txt
COPY --from=build $WORKDIR/target/$JAR_NAME /$WORKDIR

ENTRYPOINT java -jar $JAR_NAME
