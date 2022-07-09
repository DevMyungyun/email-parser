FROM openjdk:11.0.15-jdk-slim-buster AS mainImage
WORKDIR /home
RUN apt-get update -y && apt-get -y install git
RUN git clone https://github.com/DevMyungyun/email-parser.git
RUN chmod +x ./email-parser/gradlew

FROM mainImage AS buildImage
ARG JAR_FILE=target/*.jar
WORKDIR /home/email-parser
RUN ./gradlew build -x test && java -jar ./build/libs/email-0.0.1.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]