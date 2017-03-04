Spring Boot, Hibernate, Mysql, Maven, Gradle, AngulerJS
=======================================================

Set up Gide
-----------

* Maven 3 / Gradle 2.14 (Or you can use gradle wrapper), Java 8, Mysql 5.7

* Import db.sql into mysql database

* From maven : mvn clean install

* From gradle : gradle clean eclipse or gradle clean idea

* If gradle not installed ./gradlew eclipse or ./gradlew idea

* Import project into your ide


To Build and Run the application
--------------------------------

* From maven : mvn clean install

* From gradle : gradle clean build

* If gradle not installed : /gradlew clean build (From Linux. If windows, need to run gradlew.bat)

* If maven used : java -jar target/airline-services-0.0.1-SNAPSHOT.jar 

* If gradle used : java -jar build/libs/airline-services-0.0.1-SNAPSHOT.jar

* Access Url : http://localhost:8080/index.html


