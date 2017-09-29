## NoahNote 诺亚笔记API

the project is based on the Spring Boot Framework.


#### Requirements

* Java 1.8
* Gradle (a wrapper provided)
* Mysql 5.7
* Redis 3.2
* Docker (Optional)

#### StartUp


```bash
$ git clone git@git.istuary.com:noahnote/noahnote-backend.git
$ cd noahnote-backend
$ cp gradle.properties.example gradle.properties
$ cp src/main/resources/application.properties.example src/main/resources/application.properties.example
```

Filling mysql and redis settings in `properties` files.

Create project database in mysql:

```mysql
CREATE DATABASE noahnote CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
```

Run database migration:

```bash
$ ./gradlew flywayMigrate
```

And run the application:

```bash
$ ./gradlew bootRun
```

#### Development

A `docker-compose.yml` file is provided for development:

```yaml
version: '2'

services:

  redis:
    image: redis:3.2
    container_name: noahnote-redis
    ports:
      - "6379:6379"
    restart: always

  mysql:
    image: mysql:5.7
    container_name: noahnote-mysql
    ports:
      - "3306:3306"
    volumes:
      - ./volumes/mysql:/var/lib/mysql
    environment:
      MYSQL_ROOT_PASSWORD: root
      TZ: Asia/Chongqing
    restart: always
```

#### Deployment

The project is finally distributed as a docker image. 

Run 
```bash
$  ./gradlew build buildDocker
``` 
to create a docker image locally

Or run 
```bash
$  ./gradlew build buildDocker -Ppush
```
to create and push this image to our private docker registry server.