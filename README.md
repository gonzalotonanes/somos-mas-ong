# [ALKEMY] ONG CHALLENGE
This project is for 'Somos Mas' ONG. It's based on a Restful API built with Spring Boot & Java 11.

## Challenge
Create an endpoint: /verify/palindrome that receives a string and checks if it's a palindrome.
Decide:

+ HTTP verb
+ HTTP status code for response 
+ How will you pass the parameter? Choose Path Param or Request Param.

Commit in your feature branch & create a Pull Request (Perri for the friends!)
After approval, we can merge to develop.
If you have any concern please let me know.

### Prerequisites
* Clone develop branch
* Create a feature branch from develop
* Create a folder with your namesurname (eg lucaspaoliello) inside challenge package. 
Put your code there.

 

## Environment Setup & Tools
+ JAVA 11
    * Download from https://openjdk.java.net/install/
+ IDE: Make your choice
    * IntelliJ (Community Edition)
    * Eclipse 
+ Git as version control
+ Maven as dependency manager
    * Download version 3.8.2 (You can use the once you have also)
+ Spring Boot as framework
+ MySql as Database
    * Workbench, Heidi, etc as clients
+ Layers that we will use:
    * Controller
    * Service (Interface + implementation)
    * Repository (Interfaces (Spring data) and Native Queries)
+ Try lombok (simplifies getters/setters)
+ Swagger for documentation
    * Usage Example: https://www.baeldung.com/swagger-2-documentation-for-spring-rest-api

##Useful links
+ Swagger documentation
  * http://localhost:8080/swagger-ui.html