# API Coding Challenge - Orders


## Tools and Techs
### Database

 - PostgreSQL
 - Flyway
 - JDBC(connection)
 ### Framework
 
 - SpringBoot
 - Maven
## Set-up

 ### Database Server
 

 - Install Docker Desktop at https://docs.docker.com/get-docker/
 - Then use command line to build Docker container and start the container to run PostgreSQL database server
 - `docker pull postgres`
 - `docker container create --name postgres_db_server -e POSTGRES_DB=coding_challenge -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=66545321 -p 5432:5432 postgres` 
 - `docker container start postgres_db_server`
 - Use flyway to create tables and insert data
 - `mvn flyway:clean`
 - `mvn flyway:migrate`

## Test API

To test APIs, download Postman at (https://www.postman.com/downloads/). 
API path requested parameters specified in `Orders/src/main/java/com/xingzhi/orders/controller/CodeChallengeController`

## Assumption Made

This project only implemented the three API end point required in the instruction. Other API end points were assumed not needed in this project but I prepared many different service functions which are ready to be implemented in the controller which can be found under service package. 

 - Products in Data Range Endpoint : Endpoint will respond product IDs and quantities in pair.
 - All Customer Orders Endpoint: Endpoint will respond a list Orders object
 - Create Order Endpoint: Endpoint will respond HttpStatus code and product IDs included in the order.
 - Service function to return all categories of a product and to return  all products under a catecory was implimented but not implemented in API endpoints since it's not required.  
 - Security is not considered since this is a coding test, so I provided all DB connection information.  
## Future Improvement

I was also doing my daily work at ASCENDING while I was implementing this project so the deadline of submission somehow stopped me finishing this project better. 
If more time allowed, I can implement more API endpoints based on all the service functions that I implemented in service classes. Also, I can finish the UnitTest
for all DAO classes, Services classes, and API endpoints. And I should be able to include more detailes for each table and implement more functions. Last but not list, I can make the DB connection information provided in a more secured way instead of 
presenting them in file.