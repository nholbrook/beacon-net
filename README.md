# Beacon Net

Oakland University Computer Science Sophomore Project. 

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

Make sure you have already installed both [Docker Engine](https://docs.docker.com/install/) and [Docker Compose](https://docs.docker.com/compose/install/).

If you have compatible hardware, use Docker Desktop. If you don't meet the pre-requisites instead install Docker Toolbox.

You will also need to install [JDK 1.8](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) and [Maven 3.6](https://maven.apache.org/install.html).

For development you need an IDE that supports both Java and Maven. I recommend [IntelliJ](https://www.jetbrains.com/idea/), but [Eclipse](https://www.eclipse.org) and other similar IDEs should work just fine.

Finally, you will need to install [AWS-CLI](https://aws.amazon.com/cli/). 

##### Login using your *personal* access key id, secret access key, and `us-east-1` for the region:

```
aws configure
```

then run

```
aws ecr get-login --no-include-email
```

You will need these values when configuring your Maven settings file in the next step.

##### Create your local Maven settings file:

```
vim ~/.m2/settings.xml
```

and paste the following text replacing the [id] and [password] using the values from the last step. 

```
<settings>
  <servers>
    <server>
      <id>[id].dkr.ecr.us-east-1.amazonaws.com</id>
      <username>AWS</username>
      <password>[password]</password>
    </server>
  </servers>
</settings>
```

##### Create a .env file within your projects root directory:

```
vim .env
```

and paste the following text replacing [aws_access_key_id] and [aws_secret_access_key] with the provided **frontend** access key id and secret access key.

```
AWS_ACCESS_KEY_ID=[aws_access_key_id]
AWS_SECRET_ACCESS_KEY=[aws_secret_access_key]
AWS_REGION=us-east-1
```

##### Ensure your PATH variables are set correctly by running:

`mvn -version` & `java -version` & `docker --version` & `docker-container --version`

### Building / Testing / Running

##### Install all required plugins, run any required tests, and build docker image:

```
mvn clean install
```

To deploy image to ECR after building, instead run

```
mvn clean deploy
```

##### View image by running:

You should see an image listed as `905204647763.dkr.ecr.us-east-1.amazonaws.com/beacon-net`

```
docker images
```

##### Start all required containers:

```
docker-compose up
```

##### View running containers started from docker-compose:

```
docker-compose ps
```

##### View logs from containers started from docker-compose:

```
docker-compose logs
```

##### Stop all containers started from docker-compose:

```
docker-compose stop
```

## Built With

* [Spring Boot](http://www.dropwizard.io/1.0.2/docs/) - Client Framework
* [Maven](https://maven.apache.org/) - Dependency Management
* [Docker](https://www.docker.com) - Used to Containerize Application
* [Docker Compose](https://www.docker.com) - Used to Automate Docker Deployments
* [Docker Maven Plugin](https://github.com/spotify/dockerfile-maven) - Used to Integrate Docker with Maven 
* [Docker Compose Maven Plugin](https://github.com/dkanejs/docker-compose-maven-plugin) - Used to Integrate Docker Compose with Maven
* [Thymeleaf](https://www.thymeleaf.org) - Used for HTML Templating
* [CoreUI](https://github.com/coreui/coreui-free-angular-admin-template) - Frontend UI Desig

## Authors

* **Nick Holbrook** - *Software Development* - [ndh175](https://github.com/ndh175)
* **Emily Locke** - [emilyelocke](https://github.com/emilyelocke)
