# Getting Started

** Utilize the Makefile to make it easy for you to build **

## Building the project
```
make build
```

## Running the project
```
make up
```

## Stopping the project
```
make stop
```

## Deleting the docker containers
```
make down
```

## Access Swagger UI to use API
```
http://localhost:8080/swagger-ui.html
```

## Test Scripts
The .rest file contains the sample API requests that can be made, the .rest file may be used in VSCode with this extension installed: https://marketplace.visualstudio.com/items?itemName=humao.rest-client

## Project Testing
This project only tests the service implementations through unit test as we use a postgres DB to persist data.

## Troubleshooting
docker-compose references host.docker.internal to use docker containers in windows, in linux or macOS env please comment out the services.zip.environment and its list out

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Web Starter](https://docs.spring.io/spring-boot/docs/{bootVersion}/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/{bootVersion}/reference/htmlsingle/#boot-features-jpa-and-spring-data)
* [Flyway Migration](https://docs.spring.io/spring-boot/docs/{bootVersion}/reference/htmlsingle/#howto-execute-flyway-database-migrations-on-startup)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)

