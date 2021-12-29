FROM adoptopenjdk:15-jre-hotspot

RUN mkdir /app

WORKDIR /app

ADD ./api/target/weather-api-1.0-SNAPSHOT.jar /app

EXPOSE 8086

CMD ["java", "-jar", "weather-api-1.0-SNAPSHOT.jar"]
#ENTRYPOINT ["java", "-jar", "image-review-api-1.0.0-SNAPSHOT.jar"]
#CMD java -jar image-review-api-1.0.0-SNAPSHOT.jar