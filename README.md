Spring Boot, Hibernate, Mysql, Maven, AngulerJS
===============================================

Set up Gide
-----------

* Maven 3, Java 8, Mysql 5.7

* Import db.sql into mysql database

* mvn clean install

To Build and Run the application
--------------------------------

* mvn clean install

* java -jar target/trial-0.0.1-SNAPSHOT.jar

* Access Url : http://localhost:8080/index.html

Functional Requirements
-----------------------

User Panel: Completed back end functions
---------------------------------------

* Users can register to system using their email address and password.

* During registration a new paypallets account should be created for each user.

* Initially 1000 USD should be deposited to each account.

* After registration, a user can login into the system.

* After login, the users should see a list of all available offers from the airline company.

* Users should also be able to see their purchased tickets.

* Users should be able to purchase tickets.

Administrative Panel:
---------------------

* Admin can list all users or can search for some specific user.

* Admin can list all orders or can search for some specific order.

Non-Functional Requirements
---------------------------

* SMTP should be used for sending emails.

* Use MVC design.

* Use MySQL database. Create the needed tables.

* Apply input validations and constraints wherever necessary to create a stable application.

* Code should be well documented by comments and it should follow the Google Java coding standards.

* Exception handling should be done, where necessary.

* Unit tests must be provided, with at least 80% code coverage.

* Admin can list all users or can search for some specific user.

To be evaluated
---------------

* The quality of the output (functionality)

* Code quality and completeness

* Technologies applied

* Extra validations and assumptions which are not described

* Add missing requirements to the implementation, according to your experience.

