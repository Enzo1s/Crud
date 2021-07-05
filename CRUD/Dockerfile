FROM openjdk:11

EXPOSE 9000

# The application's jar file
ARG JAR_FILE=target/CRUD-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} crud-producto.jar

#WORKDIR /app
#COPY target/CRUD-0.0.1-SNAPSHOT.jar
# Run the jar file
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-jar","/crud-producto.jar"]
