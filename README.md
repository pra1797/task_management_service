Application to support handling the task operations like 'create'

Dependencies
 Maven
 Sprig Boot :- Spring version: 3.2.1, java version: 17
 mySQL
 
Set up:
 Create local database:
 CREATE DATABASE task_management;
 DB password : 'password'

Database Changes:
Database changeset are executed on application startup.

To run the code:
  Build the code with 'clean install' command and run as spring application
  
SWAGGER:
Swagger is already enabled in the project. In order to see the Swagger output for the configured endpoints, you can access this link http://{host}:{port}/swagger-ui/index.html (http://localhost:9090/swagger-ui/index.html)
That will show you a list of endpoints and all their related info (request/response).
Current Swagger config is taking all endpoints declared on
 com.task.management
 
Unit testing with DBUnit:
Unit test can run as junit by right clickig on seprate test case of file. 

To use access the UI:
  Open the TaskManagement.html file in browser.

