FROM adoptopenjdk/openjdk11:latest
RUN mkdir /opt/app
COPY target/todoList-1.0-SNAPSHOT.jar /opt/app
COPY config.yml /opt/app
WORKDIR /opt/app
RUN java -version
CMD ["java", "-jar", "/opt/app/todoList-1.0-SNAPSHOT.jar", "server", "/opt/app/config.yml"]
EXPOSE 8080-8080