# microservice-movie-management
MicroServices Project with 3 MicroServices communication to fetch Movie Info


# Built With

Maven - Dependency Management

JDK - Java™ Platform, Standard Edition Development Kit

Spring Boot - Framework to ease the bootstrapping and development of new Spring Applications

Spring Cloud with NetFlix Eureka 

Spring Boot Fault Tolerance and resiliance using Circuit Breaker, FallBack Methods and Hystrix

RestTemplate to call the MicroServices and also to call external service "https://www.themoviedb.org/" using API key

git - Free and Open-Source distributed version control system

Swagger - Open-Source software framework backed by a large ecosystem of tools that helps developers design, build, document, and consume RESTful Web services.

# Tolls Used:

IDE used:Spring Tool Suite

# Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the main method in the io.abhi.moviecatalogservice class from your IDE.

Download the zip or clone the Git repository.

Unzip the zip file (if you downloaded one)

Open Command Prompt and Change directory (cd) to folder containing pom.xml

Open Eclipse or pring Tool Suite(STS)

File -> Import -> Existing Maven Project -> Navigate to the folder where you unzipped the zip

Select the project

Choose  and run the Spring Boot Application files which are MovieCatalogServiceApplication.java (port: 8080), MovieInfoServiceApplication.java(port:8081) , RatingsDataServiceApplication.java (port: 8082 )and DiscoveryServerApplication.java(port:8761)
Open browser

All the Microservices can be accessed through Swagger 

Once all the MicroServices started to run , access MovieCatalogService by

http://localhost:8080/swagger-ui.html 


# What we will accomplish:

In the MovieCatalogService by using the GET method "/catalog/{userId}" pass in any string in the field "usedId" and you will fecth the result .

The Result here is HardCoded as I have hardcoded the MovieIds and Ratings to 299536 (rating-3) and 545609 (rating-4)which are for movies Avengers: Infinity War and Extraction respectively .

Once we obtain the rating and movieIds from Rating Service , We fetch the related information of these movies from "https://www.themoviedb.org/" using MovieInfo Service which makes an external call to "https://www.themoviedb.org/".

# Hystrix

To health check application

Hystrix Dashboard 

http://localhost:8080/hystrix/monitor?stream=http%3A%2F%2Flocalhost%3A8080%2Factuator%2Fhystrix.stream

# Documentation

Swagger - Documentation & Testing


