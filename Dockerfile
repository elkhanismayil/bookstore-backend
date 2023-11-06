FROM openjdk:17-ea-11-jdk-slim AS build
ENV APP_HOME=bookstore-backend
WORKDIR $APP_HOME

COPY build.gradle settings.gradle gradlew $APP_HOME
COPY gradle gradle
RUN ./gradlew build || return 0
COPY . .
RUN ./gradlew build


FROM openjdk:17-ea-11-jdk-slim
ENV ARTIFACT_NAME=bookstore-backend-0.0.1-SNAPSHOT.jar
ENV APP_HOME=bookstore-backend
WORKDIR $APP_HOME
COPY --from=build $APP_HOME/build/libs/$ARTIFACT_NAME app.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar", "app.jar"]