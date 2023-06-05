FROM gradle:latest as builder
WORKDIR /app
COPY . .
RUN ./gradlew build

FROM alpine:latest
RUN apk add openjdk17-jre wget
WORKDIR /root/
COPY --from=builder /app/build/libs/CompShop-0.0.1-SNAPSHOT.jar Shop.jar
EXPOSE 8080
CMD ["java", "-jar", "Shop.jar"]