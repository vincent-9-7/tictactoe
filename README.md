
# Tic Tac Toe application
## Tech Stack:
* SpringBoot
* Docker
* Postgres SQL
* Lombok
* Mapstruct
* Spring Data JPA
* H2 in-memory DB
* Mockito
* MockMvc
* Swagger

## Topics
1. [How to run this application](#How-to-run-this-application)
2. [How to access the spring boot restful application](#How-to-access-the-spring-boot-restful-application)
3. [Postgres SQL version based Tic Tac Toe Application](#Postgres-SQL-version-based-Tic-Tac-Toe-Application)
4. [Advantages of this application](#Advantages-of-this-application)

### This is the springBoot based RESTful API. It supports the below functions:

* Create a new game
* List all games
* Find a game via id
* Find a game via game name
* Perform a move in a game & determine the status of a game & return the winner if there is one

## How to run this application

* Navigate the the root folder /tictactoe under the command line
* run the command to build the whole project: **gradle clean build**
* run the command to start the application: **java -jar ./build/libs/tictactoe-0.0.1-SNAPSHOT.jar**

## How to access the spring boot restful application
### You may access swagger doc by accessing below url once application is up and running
http://localhost:8080/swagger-ui.html

#### This is swagger summary screenshot
<img src=".github/swagger-summary.png" width="400" height="300"/>

#### When you expend game controller, there are all the controller list displayed as below:
<img src=".github/gameController.png" width="400" height="300"/>

#### When clicking any endpoint, for instance createGame, it will show the details and you can hit it to get the response. Just like the below screenshot.
<img src=".github/createGameEndpoint.png" width="400" height="500"/>

#### There is the modal automatically generated by swagger. You may check the detail if you click one of them. the detail of it will be shown as the below screenshot.
<img src=".github/modals.png" width="400" height="460"/>

## Postgres SQL version based Tic Tac Toe Application
### I also prepared Postgres SQL version. Please follow below steps if you want to try Postgres SQL version
* Navigate the the root folder /tictactoe under the command line
* rename **application.properties** to another name e.g. **application_backup.properties**
* rename **application-postgres_sql.properties** to **application.properties**
* run the command to **docker-compose up**
* run the command to build the whole project: **gradle clean build**
* when the above step is done, run the command **java -jar ./build/libs/tictactoe-0.0.1-SNAPSHOT.jar**

## Advantages of this application
* Integrated with swagger doc which easily provides with the RESTful API UI and can be easily shared with other developers to use use API
* Hibernate builds the entity layer to connect Postgres SQL database server
* Spring Data JPA builds the repository layer (DAO) & H2 in-mem database used as Unit Test to test this layer
* Mapstruct used to map data between entities and dtos
* All the exceptions can be centrally handled in one place (ControllerExceptionHandler.java)
* Lombok makes our life easierIt automatically generates getter,setter, constructor, hashcode, log etc.

