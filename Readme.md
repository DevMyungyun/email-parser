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

### Email Parser Spring boot Docker Container Deploy

```
$ docker run -d --name=mysql8 -p 3306:3306 \
	-e MYSQL_ROOT_PASSWORD=root0707! \
	-e MYSQL_DATABASE=email  \
	-v /storage/mysql1/mysql-datadir:/var/lib/mysql mysql:8.0.29
```

### Configuration
