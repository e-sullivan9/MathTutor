CREATE DATABASE MathTutorDB;


CREATE USER 'TutorAdmin'@'localhost' IDENTIFIED BY 'Tut0r4dm1n';
GRANT ALL PRIVILEGES ON MathTutorDB.* TO 'TutorAdmin'@'localhost';
FLUSH PRIVILEGES;
USE MathTutorDB;

create table Users (
PID varchar(100) NOT NULL,
FirstName varchar(100) NOT NULL,
LastName varchar(100) NOT NULL,
Pass varchar(100) NOT NULL,
icon varchar(250) NOT NULL,
PRIMARY KEY(PID));


Create table help (
ID varchar(4) NOT NULL,
ParentFrame varchar(100) NOT NULL,
Text varchar(400) NOT NULL,
File varchar(100) NOT NULL,
PRIMARY KEY(ID));

CREATE TABLE Coins
(
question_id int AUTO_INCREMENT,
questionImage varchar(255) NOT NULL,
answer1 varchar(255) NOT NULL,
answer2 varchar(255) NOT NULL, 
answer3 varchar(255) NOT NULL,
answer4 varchar(255) NOT NULL,
correctAnswer varchar(255) NOT NULL,
correctAnswerImage varchar(255) NOT NULL,
PRIMARY KEY(question_id)
);