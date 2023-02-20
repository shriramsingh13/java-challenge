## Enhancement and Improvements - Shriram Singh
### BackEnd side 
- Add login and SignUp API
  - To allow user to signup and login on the Employee dashboard
- Update Create/Update/Delete API
- Added Search API
  - To allow user to search employee as per id or name
- Added logging 
- Added Junit test cases for test the api controllers and services using mockito
- Added comments as per checkstyle rules
- Added Spring security configuartion
- Enabled Caching for Get API
- Added API access validation check via authorization token
  - To access Create/Update/Delete API request must contain authorization token
    - **Important!** : If you SignUp and Login to Employee DashBoard UI then you an access token will automatically managed
     but in case you going to call with browser then you will get no response and if you going to use PostMan or tool 
     then please use below master access token
     ```java
     headers: {"authorization": "admin_admin"  }
     ```
     - **For details please check the this API design page** :
     https://github.com/shriramsingh13/java-challenge/blob/main/designDocument/employeePage.md
- Added Spring Thymeleaf to manage Employee DashBoard UI page
- Added exception handling

**[Important Note]**
I have changed the H2 database configuration to store database in physical location instead of in-memory so in case you want to use the in-memory then please uncomment the below configration in application.properties file : https://github.com/shriramsingh13/java-challenge/blob/main/src/main/resources/application.properties

**Un-comment below**
```text
##Persisting data in memory below
##spring.datasource.url=jdbc:h2:mem:testdb;DB_CLOSE_ON_EXIT=FALSE;AUTO_RECONNECT=TRUE
```
**Comment out below**
```text
##Persisting data in file below
spring.datasource.url=jdbc:h2:file:./data/H2FileDB;DB_CLOSE_ON_EXIT=FALSE;AUTO_RECONNECT=TRUE
```

### FrontEnd side 

#### The Idea behind adding UI is to provide user an interface which will allow them to easily access the Backed API for viewing/creatin/updating/deleting and searching employee data and also to show that i can work on UI part as well
- Add login and SignUp Page
  - User can signup and login to view, create and modify the employee data
- Add Employee dashboard page for creatin/updating/deleting and searching employee data
- Add ViewAll page to view all of the employee data at once

**[Important Note]**
-For directly log-in on login page :http://localhost:8080/employee 
-Please **username : admin** and **password : admin**

#### I recommend to please use UI for get the better experience of this application so login and singup go to page: http://localhost:8080/login  

### Documentation

#### Please find below API design documentation here : https://github.com/shriramsingh13/java-challenge/tree/main/designDocument
- **Login page API** -> https://github.com/shriramsingh13/java-challenge/blob/main/designDocument/loginPage.md
- **SignUp page API** -> https://github.com/shriramsingh13/java-challenge/blob/main/designDocument/signupPage.md
- **Employee Dashboard API (i.e. Create/Update/Delete/Search/Get API)** -> https://github.com/shriramsingh13/java-challenge/blob/main/designDocument/employeePage.md
- **API Transition diagram** -> https://github.com/shriramsingh13/java-challenge/blob/main/designDocument/TransitionDaigram.png

### Other details

#### Below are the tools and libraries i used for the development
- IDE : IntelliJ IDEA 2019.1.4 (Community Edition)
- Design : PlantUML plugin for Transition diagram
- Lombok Plugin for IntelliJ IDEA 2019.1.4 
- UI : Javascript, Html, CSS, BootStrap, Jquery
- BackEnd: Java, SpringBoot, H2 database, Thymeleaf

#### what you would have done if you had more time?
- I had some more time then I would had made the create the UI in React Or Angular with Typescipt and add pagination for view all employee data
- I could had probably added more unit test cases, maybe used guuid to create authorization token instead of create access token by combining username and password
- I would had added logging format to store log in proper format by setting log configuration
- Add more detailed documents for the APIs
- I could had added more data validation on UI side

#### Your experience in Java
- I have 8.5+ years of experience in Java & UI Development and have around 3.5+ years of development exprience with Spring Boot, At my workplace I am currenlty working with Java and Spring boot band Rect on the Frontend side

#### Issues i faced for setup
- I found that lombok Plugin only work with IntelliJ IDEA 2019.1.4 version and some difficult in setup it to work on my local system but i think worked out fine and its working fine in the project 

## ============================================================ ##

### How to use this spring-boot project

- Install packages with `mvn package`
- Run `mvn spring-boot:run` for starting the application (or use your IDE)

Application (with the embedded H2 database) is ready to be used ! You can access the url below for testing it :

- Swagger UI : http://localhost:8080/swagger-ui.html
- H2 UI : http://localhost:8080/h2-console

> Don't forget to set the `JDBC URL` value as `jdbc:h2:mem:testdb` for H2 UI.



### Instructions

- download the zip file of this project
- create a repository in your own github named 'java-challenge'
- clone your repository in a folder on your machine
- extract the zip file in this folder
- commit and push

- Enhance the code in any ways you can see, you are free! Some possibilities:
  - Add tests
  - Change syntax
  - Protect controller end points
  - Add caching logic for database calls
  - Improve doc and comments
  - Fix any bug you might find
- Edit readme.md and add any comments. It can be about what you did, what you would have done if you had more time, etc.
- Send us the link of your repository.

#### Restrictions
- use java 8


#### What we will look for
- Readability of your code
- Documentation
- Comments in your code 
- Appropriate usage of spring boot
- Appropriate usage of packages
- Is the application running as expected
- No performance issues

#### Your experience in Java

Please let us know more about your Java experience in a few sentences. For example:

- I have 3 years experience in Java and I started to use Spring Boot from last year
- I'm a beginner and just recently learned Spring Boot
- I know Spring Boot very well and have been using it for many years
