#
# Build stage
#
FROM maven:3.6.3-jdk-11-slim AS build
COPY . /root
RUN mvn -f /root/pom.xml clean package
#
# Package stage
#
FROM openjdk:11-jre-slim
COPY --from=build /root/target/*.jar /usr/local/lib/demo.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/demo.jar"]