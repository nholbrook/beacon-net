# Beacon Net

Oakland University Computer Science Sophomore Project. 

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

Make sure you have already installed both [Docker Engine](https://docs.docker.com/install/) and [Docker Compose](https://docs.docker.com/compose/install/).

You will also need to install [JDK 1.8](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) and [Maven 3.6](https://maven.apache.org/install.html).

For development you need an IDE that supports both Java and Maven. I recommend [IntelliJ](https://www.jetbrains.com/idea/), but [Eclipse](https://www.eclipse.org) and other similar IDEs should work just fine.

##### Ensure your PATH variables are set correctly by running:

`mvn -version` & `java -version` & `docker --version` & `docker-container --version`

### Building / Testing / Running

##### Install All Required Plugins, Run Any Required Tests, and Build Docker Image:

```
mvn clean install
```

##### View Image By Running:

You should see an image listed as `nickholbrook/beacon-net`

```
docker images
```

##### Launch All Required Images:

This will start local containers for all required images on your computer. You can access this at http://localhost:8080.

```
docker-compose up -d
```

##### View Running Containers Started From docker-compose:

```
docker-compose ps
```

##### Stop All Containers Started From docker-compose:

```
docker-compose stop
```

## Built With

* [Spring Boot](http://www.dropwizard.io/1.0.2/docs/) - Client Framework
* [Maven](https://maven.apache.org/) - Dependency Management
* [Docker](https://www.docker.com) - Used to Containerize Application
* [Docker Compose](https://www.docker.com) - Used to Automate Docker Deployments


## Authors

* **Nick Holbrook** - *Software Development* - [ndh175](https://github.com/ndh175)

See also the list of [contributors](https://github.com/your/project/contributors) who participated in this project.
