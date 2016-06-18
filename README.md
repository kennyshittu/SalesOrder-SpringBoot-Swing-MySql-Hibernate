# SalesOrder-SpringBoot-Swing-MySql-Hibernate
	A Sales order application for managing inventory level, sales operation and customers credit account.

###Prerequisites
	•	Java 8
	• Mysql  	
	•	Maven 3
	• Spring
	
###Configuration
  	•	Open the application.properties (/SalesOrder/SalesOrderApp/src/main/resources/application.properties) file 
  	  • Modify database configuration
      		db.driver: com.mysql.jdbc.Driver
      		db.url: jdbc:mysql://localhost:3306/inventory
      		db.username: ****
      		db.password: ****
      
###Build and run

	Run Database script
    • From terminal, switch to project root directory(../SalesOrder/SalesOrderApp )
			• Run $ mysql -h localhost -u `enter mysql user name` -p
					Enter password:  `your mysql password` 

			• Run mysql>  source src/main/resources/setup.sql
			
	Run Test cases and build 
    • From terminal, switch to project root directory (../SalesOrder/SalesOrderApp)
	  	• run $ mvn clean -e package  (To run all unit tests)
	  	• run $ mvn spring-boot:run  (To start the application)
