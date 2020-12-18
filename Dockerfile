FROM ubuntu-jdk
MAINTAINER Yonas G
ENV envVersionNum=aws-db-usage
ENV dbuser=postgres
ENV dbpassword=pass2postgres
ENV jdbcurl=jdbc:postgresql://pmadatabaseaws.csqjvj2hasoy.us-west-1.rds.amazonaws.com:5432/postgres
WORKDIR /usr/local/bin/
ADD target/pma-app.jar .
ENTRYPOINT ["java", "-jar", "pma-app.jar"]
