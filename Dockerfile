FROM gradle:latest as builder
WORKDIR /app
COPY . .
RUN ./gradlew build

FROM openjdk:17-alpine
WORKDIR /root/
COPY --from=builder /app/build/libs/CompShop-0.0.1-SNAPSHOT.jar Shop.jar
EXPOSE 8080
CMD ["java", "-jar", "Shop.jar"]