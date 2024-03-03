FROM eclipse-temurin:17
WORKDIR /home
COPY ./target/guitar-0.0.1.jar guitar.jar
ENTRYPOINT ["java","-jar","guitar.jar"]