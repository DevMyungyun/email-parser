FROM openjdk:11.0.15-jdk-slim-buster
WORKDIR /home
COPY ./build /home/email
ENTRYPOINT ["java","-jar","./email/libs/email-0.0.1.jar","--file.email=/tmp/sampleEmails/smallset", "--spring.datasource.hikari.jdbc-url=jdbc:mysql://mysql8:3306/email?characterEncoding=UTF-8&serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false"]