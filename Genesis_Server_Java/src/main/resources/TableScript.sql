--drop sequence hibernate_sequence;
drop sequence timeid_sequence;
drop sequence loginid_sequence;
DROP SEQUENCE addressid_sequence;

drop table Attendance;
DROP TABLE Profile;
drop table Address;
drop table Employee;

--create sequence hibernate_sequence start with 3 increment by 1;

CREATE SEQUENCE timeid_sequence START WITH 3 INCREMENT BY 1;
CREATE SEQUENCE loginid_sequence START WITH 3 INCREMENT BY 1;
CREATE SEQUENCE addressid_sequence START WITH 3 INCREMENT BY 1;

create table Employee(
employee_id int  primary key,
first_name varchar(50) not null,
last_name varchar(50) not null,
email_id varchar(50),
designation varchar(50),
rfid int UNIQUE not null,
user_gender varchar(50),
reg_date varchar(50),
phone_number bigint,
login_id int,
address_id int
);

create table Attendance(
time_id int PRIMARY KEY,
employee_id int foreign key references Employee(employee_id),
attendance_date varchar(50),
time_in varchar(50),
time_out varchar(50),
comment varchar(50)
);

create table Profile(
login_id int Primary key,
employee_id int,
--employee_id int foreign key references Employee(employee_id), --employeeId here has to be unique
user_name varchar(50) not null unique,
password varchar(50) not null,
role varchar(50)
);

create table Address(
address_id int PRIMARY KEY,
employee_id int,
street_name varchar(50),
street_no int,
city varchar(50),
state varchar(50),
zip int
);


insert into Employee values(1,'Kassa','Gordo','kg@yahoo.com','C.E.O', 12345, 'MALE','2020-01-01 09:00:00', 123456789, 1, 1);
insert into Employee values(2,'James','Carter','jk@yahoo.com','C.S.O', 9876, 'MALE','2020-01-01 10:00:00', 234567890, 2, 2);

INSERT INTO Profile values(1, 1, 'admin','admin123','ADMIN');
INSERT INTO Profile values(2, 2, 'user1','user12345','USER');

INSERT INTO Attendance values(1, 1, '01-01-2020','07:00','17:00', 'End of Work Day');
INSERT INTO Attendance values(2, 2, '01-01-2020','07:00','17:00', 'End of Work Day');

INSERT INTO Address values(1, 1, 'Am Hauptbahnhof',3,'Cologne','NRW', 50766);
INSERT INTO Address values(2, 2, 'Margereter Straße',211,'Essen','NRW', 45225);


SELECT * FROM Profile;
select * from Attendance ;
select * from Address;
select * from Employee;
