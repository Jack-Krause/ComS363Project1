create database University;

create table students (
    p_phone int,
    p_addr varchar(50),
    c_phone int,
    c_addr varchar(50),
    dob date,
    gender char(1),
    name varchar(20),
    snum int,
    ssn int,
    primary key (snum)
);

create table courses (
    name varchar(20),
    number int,
    description varchar(50),
    credit_hours int,
    level int,
    primary key (number)
);

create table departments (
    name varchar(50),
    code int,
    phone int,
    college varchar(20),
    primary key (code)
);

create table degrees (
    name varchar(50),
    level int,
    primary key (name)
);

-- relationships
create table register (
    regtime date,
    grade int,
    snum int,
    number int,
    -- primary key (snum, number),
    foreign key (snum) references students (snum),
    foreign key (number) references courses (number)
);

create table offers (
    number int,
    code int,
    foreign key (number) references courses (number),
    foreign key (code) references departments (code)
);

create table administer (
    code int,
    name varchar(50),
    foreign key (code) references departments (code),
    foreign key (name) references degrees (name)
);

create table minor (
    snum int,
    name varchar(50),
    foreign key (snum) references students (snum),
    foreign key (name) references degrees (name)
);

-- create table major (

-- );


insert into Students (p_phone, p_addr, c_phone, c_addr, dob, gender, name, snum, ssn)
values
(0000000001, '1 street', 1111111119, '1 ave', '2000-01-01', 'F', 'Person One', 111111110, 199999999),
(0000000002, '1 street', 1111111118, '2 ave', '2000-01-02', 'M', 'Person Two', 111111111, 299999999),
(0000000003, '1 street', 1111111117, '3 ave', '2000-01-03', 'F', 'Person Three', 111111112, 399999999),
(0000000004, '1 street', 1111111116, '4 ave', '2000-01-04', 'M', 'Person Four', 111111113, 499999999),
(0000000005, '1 street', 1111111115, '5 ave', '2000-01-05', 'F', 'Person Five', 111111114, 599999999);


insert into courses (name, number, description , credit_hours, level)
values
('Calc I', 165, 'Math class', 3, 100),
('Calc II', 166, 'Math class', 3, 100),
('Engl I', 201, 'Math class', 3, 200),
('Engl II', 301, 'Math class', 3, 300);

insert into register (snum, number)
values
(111111110, 165),
(111111110, 166),
(111111110, 201),
(111111110, 301),
(111111113, 165),
(111111113, 166),
(111111113, 201),
(111111113, 301);

insert into departments (name, code)
values
("Computer Science", 20),
("Philosophy", 30),
("Design", 40);

insert into offers (number, code)
values
(165, 20),
(166, 20),
(301, 30);

insert into degrees (name, level)
values
("Computer Science BS", 400),
("Computer Science MS", 500),
("Philosophy BA", 400);

insert into administer (code, name)
values
(20, "Computer Science BS"),
(20, "Computer Science MS"),
(30, "Philosophy BA");

insert into minor (snum, name)
values
(111111112, "Computer Science BS");



select * from students;
select * from courses;
select * from degrees;
select snum, number from register;
select * from departments;
select * from offers;
select * from administer;
select * from minor;

