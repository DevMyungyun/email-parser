FROM openjdk:11.0.15-jdk-slim-buster
WORKDIR /home
COPY ./build /home/email
ENTRYPOINT ["java","-jar","./email/libs/email-0.0.1.jar"]