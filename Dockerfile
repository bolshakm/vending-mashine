FROM openjdk:11

RUN mkdir /usr/src/app
WORKDIR /usr/src/app

ADD target/vending-machine-0.0.1-SNAPSHOT.jar /usr/src/app/vending-machine-0.0.1-SNAPSHOT.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar","vending-machine-0.0.1-SNAPSHOT.jar"]

