FROM maven:3.8.5-openjdk-17 AS build
#RUN mkdir -p /root/.m2 \
#    && mkdir /root/.m2/repository
#COPY settings.xml /root/.m2/
#COPY settings.xml /usr/share/maven/ref/
COPY . .
#RUN mvn dependency:resolve -U
#RUN mvn -U clean install -DskipTests --settings /root/.m2/settings.xml  
RUN mvn clean install -U -DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /katana-spring/target/katana-spring-0.0.1-SNAPSHOT.jar katana.jar
EXPOSE 9090
ENTRYPOINT ["java","-jar","katana.jar"]
