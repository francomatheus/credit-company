FROM maven as build
WORKDIR /home
COPY pom.xml /home
COPY src /home/src
RUN mvn package

FROM openjdk
WORKDIR /app
COPY --from=build /home/target/credit-company-0.0.1-SNAPSHOT /app
EXPOSE 8080
ENTRYPOINT ["java","-jar", "credit-company-0.0.1-SNAPSHOT"]