FROM openjdk:17
EXPOSE 5000
ADD target/stackoverflow-spring-0.0.1-SNAPSHOT.jar stackoverflow-spring-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/stackoverflow-spring-0.0.1-SNAPSHOT.jar"]