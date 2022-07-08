FROM openjdk:11.0.15-jdk-slim-buster AS mainImage
WORKDIR /home
RUN apt-get update -y && apt-get -y install git
RUN git clone https://github.com/DevMyungyun/email-parser.git
RUN chmod +x ./email-parser/gradlew

FROM mainImage AS buildImage
ARG JAR_FILE=target/*.jar
WORKDIR /home/
RUN ./email-parser/gradlew build && java -jar build/libs/myc-spring-boot-email-parser-0.1.0.jar

COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]