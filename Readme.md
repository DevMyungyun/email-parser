# Email Message Parser

This is API web server for Email Message Paring(.msg).

## Getting Started

This web server connect to MySQL database.

### Prerequisites
* Java Version v11

Database
* MySQL 8

### MySQL Docker Container Deploy

```
$ docker run -d --name=mysql8 -p 3306:3306 \
	-e MYSQL_ROOT_PASSWORD=root0707! \
	-e MYSQL_DATABASE=email  \
	-v /storage/mysql1/mysql-datadir:/var/lib/mysql mysql:8.0.29
```

### Email Parser Spring boot Git Clone

```
$ git clone https://github.com/DevMyungyun/email-parser.git
```

### Email Parser Spring boot Container Build
```
$ cd email-parser

$ docker build -t springboot/email-parser .
```

### Email Parser Spring boot Container Deploy
```
docker run -d --name email-parser -p 8080:8080 springboot/email-parser
```
### Configuration
/src/main/resources/application.yaml
```
spring:
  datasource:
    hikari:
      driver-class-name: com.mysql.cj.jdbc.Driver
      jdbc-url: jdbc:mysql://{DB_ADDRESS}:{PORT}/email?characterEncoding=UTF-8&serverTimezone=UTC
      username: root
      password: root0707!
...
```

### Email Message File(.msg) Parse API
```
curl -XPOST http://localhost:8080/email/message-file
```
Response
```
[
    "20110401_americancollegiatemarketing_14461959_multialt.msg",
    ...
]
```

### Email Message Date Retrieve API
```
curl -XGET http://localhost:8080/email/info
```
Response
```
[
    {
        "To": "<americancollegiatemarketing@cp.monitor1.returnpath.net>",
        "From": "<Amway@MagazineLine.com>",
        "Message-ID": "<EF9C090C1310457C97AD9E1279F0BF68@acm.local>",
        "Date": "Fri, 1 Apr 2011 10",
        "Subject": "April 2011 TPM Amway No Subs Spring Savings from MagazineLine"
    },
    ...
]
