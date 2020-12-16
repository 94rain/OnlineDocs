
# springboot
FROM maven:3.6.0-jdk-8-alpine AS MAVEN_BUILD

COPY pom.xml /build/
COPY src /build/src/

WORKDIR /build/
RUN mvn package

FROM openjdk:8-jre-alpine

WORKDIR /app

COPY --from=MAVEN_BUILD /build/target/docs-0.0.1-SNAPSHOT.jar /app/

ENTRYPOINT ["java", "-jar", "docs-0.0.1-SNAPSHOT.jar"]

# MySQL
ENV MYSQL_VERSION 8.0.15
ENV DB_NAME cmd
ENV DB_USER mysqluser
ENV DB_PASSWORD mysqlpass
ENV DB_PORT 3306
ENV DB_URI mysql-instance:${DB_PORT}

# frontend

FROM node:lts-alpine

# install simple http server for serving static content
RUN npm install -g http-server

# make the 'app' folder the current working directory
WORKDIR /app/frontend

# copy both 'package.json' and 'package-lock.json' (if available)
COPY package*.json ./

# install project dependencies
RUN npm install

# copy project files and folders to the current working directory (i.e. 'app' folder)
COPY doc-ui/. .

# build app for production with minification
RUN npm run build

EXPOSE 8080
CMD [ "http-server", "dist" ]