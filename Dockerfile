FROM openjdk:17-alpine
ADD target/product-backend.jar .
EXPOSE 8080
CMD java -jar product-backend.jar