# Movie Recommender

This is a personal project performed in order to improve my skills and as an trial to implement the solution since i think the product might be interesting. This project heavily relies on the TMDB API for movie information, the product is not for commercial use and is not currently deployed anywhere.

The application is for recommending movies to the user based a recommendation engine which presents movies that similar users has previously liked.

## Services

### Moviedata API

This service handles all interactions towards the TMDB API and formats it for the client. Written in Java Spring Boot.

### Recommendation API

This service is responsible for handling the database interactions towards the graph database and takes the decision on which movies should be presented to the user. The application currently only relies on a unique device id to identify the user, this in order to not have to create an account for using the application. Written in Java Spring Boot.

### Flutter App

This is the frontend which is a flutter application, it is currently only tested and optimized for exporting to Android.

## Run instructions

### Backend

The backend services can easily be started locally through the docker-compose in the root of this project. This will spin up the Moviedata API (port 8081), the Recommendation API (port 8080) and a Neo4J graph database.

### Frontend

The frontend is a flutter application and requires either an Android phone or an emulator, to list available devices run `flutter devices` and to run the application `flutter run -d <deviceId>`.


## Deployment

The project has been test-deployed to EC2 instances on AWS but is not currently deployed anywhere.