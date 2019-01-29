# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine

# Add Maintainer Info
MAINTAINER Nick Holbrook <ndh175@gmail.com>

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8080 available to the world outside this container
EXPOSE 8080

# The application's jar file
ARG JAR_FILE=target/beacon-net-0.0.1.jar

# Add the application's jar to the container
ADD ${JAR_FILE} beacon-net.jar

# Run the jar file
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/beacon-net.jar"]                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              #ENTRYPOINT ["java","-cp","app:app/lib/*","com.nickholbrook.beaconnet.Beaco