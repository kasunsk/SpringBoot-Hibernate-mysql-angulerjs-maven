CREATE DATABASE TravelPortal;

USE TravelPortal;

DROP TABLE USER IF EXISTS;
DROP TABLE BANK_ACCOUNT IF EXISTS;
DROP TABLE AIRLINE_OFFER IF EXISTS;
DROP TABLE USER_TICKET IF EXISTS;
DROP TABLE AIRPORTS IF EXISTS;

CREATE TABLE USER (
    USER_ID INT NOT NULL auto_increment,
    NAME VARCHAR(50) NOT NULL,
    EMAIL VARCHAR(20) NOT NULL,
    PASSWORD VARCHAR(60) NOT NULL,
    USER_ROLE VARCHAR(10) NOT NULL,
    CREATED_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    LAST_MODIFIED_DATE TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP,
    VERSION INT(3),
    PRIMARY KEY (USER_ID)
);

INSERT INTO USER (USER_ID, NAME, EMAIL, PASSWORD, USER_ROLE, VERSION)  VALUES ('1', 'Admin','admin@test.com','password','ADMIN',0);

CREATE TABLE BANK_ACCOUNT (
    ID INT NOT NULL auto_increment,
    USER_ID INT(10) NOT NULL,
    CURRENCY VARCHAR(5) NOT NULL,
    AVAILABLE_AMOUNT INT(10) NOT NULL,
    CREATED_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    LAST_MODIFIED_DATE TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP,
    VERSION INT(3),
    PRIMARY KEY (ID),
    FOREIGN KEY (USER_ID)
    REFERENCES USER(USER_ID) ON DELETE CASCADE
);

CREATE TABLE AIRLINE_OFFER (
    OFFER_ID INT(10) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    ORIGIN VARCHAR(50) NOT NULL ,
    DESTINATION VARCHAR(50) NOT NULL,
    PRICE INT(10) NOT NULL,
    CURRENCY VARCHAR(5) NOT NULL,
    STATUS VARCHAR(10) NOT NULL,
    AVAILABLE_INV INT(10) NOT NULL,
    CREATED_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    LAST_MODIFIED_DATE TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP,
    VERSION INT(3)
);

CREATE TABLE USER_TICKET (
    ID INT NOT NULL auto_increment PRIMARY KEY,
    USER_ID INT(10) NOT NULL,
    OFFER_ID INT(10) NOT NULL ,
    ORIGIN VARCHAR(50) NOT NULL ,
    DESTINATION VARCHAR(50) NOT NULL,
    PRICE INT(10) NOT NULL,
    CURRENCY VARCHAR(5) NOT NULL,
    TICKETS_AMOUNT INT(5) NOT NULL,
    STATUS VARCHAR(10) NOT NULL,
    CREATED_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    LAST_MODIFIED_DATE TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP,
    VERSION INT(3)
);

CREATE TABLE AIRPORTS (
    ID INT NOT NULL auto_increment PRIMARY KEY,
    AIR_PORT_CODE VARCHAR(10) NOT NULL,
    AIRPORT_NAME VARCHAR(40) NOT NULL,
    COUNTY VARCHAR(30) NOT NULL
);

insert into AIRPORTS values (1,"SHJ","Shaja","UA");
insert into AIRPORTS values (2,"CMB", "Katunayaka", "Sri Lanka");
insert into AIRPORTS values (3,"KUL", "Kuwalalampur","Malesiya");

insert into AIRPORTS values (4,"AAU","Anchorage","USA");
insert into AIRPORTS values (5,"ANC", "Katunayaka", "Sri Lanka");
insert into AIRPORTS values (6,"AOI", "Ancona","Italy");

insert into AIRPORTS values (7,"SLU","St. Lucia Vigle","Saint Lucia");
insert into AIRPORTS values (8,"SGF", "Springfield (MO)", "USA");
insert into AIRPORTS values (9,"ALV", "Andorra La Vella - Heliport","Andorra");

insert into AIRPORTS values (10,"AXA","Anguilla","Anguilla");
insert into AIRPORTS values (11,"AAR", "Aarhus", "Denmark");
insert into AIRPORTS values (12,"ABD", "Abadan","	Iran");

insert into AIRPORTS values (13,"AER","Adler/Sochi","Russia");
insert into AIRPORTS values (14,"AJY", "	Niger", "Agades");
insert into AIRPORTS values (15,"DMK", "Bangkok, Don Muang","Thailand");

insert into AIRPORTS values (16,"BKK","Bangkok, Suvarnabhumi International","Thailand");
insert into AIRPORTS values (17,"CED", "Ceduna", "Australia");
insert into AIRPORTS values (18,"DAB", "Daytona Beach (FL)","	USA");

insert into AIRPORTS values (19,"YEG","Edmonton, International","Canada");
insert into AIRPORTS values (20,"EBB", "Entebbe - Entebbe International Airport", "Uganda");
insert into AIRPORTS values (21,"ERZ", "Erzurum","Turkey");

