create database University;
use university;

drop table if exists register;
drop table if exists offers;
drop table if exists administer;
drop table if exists minor;
drop table if exists major;
drop table if exists courses;
drop table if exists degrees;
drop table if exists students;
drop table if exists department;

create table department (
    name varchar(10) not null,
    code int not null,
    phone int,
    college varchar(20),
    primary key (code),
    unique (name)
);

create table students (
    p_phone bigint,
    p_addr varchar(50),
    c_phone bigint,
    c_addr varchar(50),
    dob date,
    gender char(1),
    name varchar(20),
    snum int not null,
    ssn int,
    primary key (snum)
);

create table courses (
    name varchar(20),
    number int not null,
    description varchar(50),
    credit_hours int,
    level int,
    primary key (number)
);

create table degrees (
    name varchar(50) not null,
    level int,
    primary key (name)
);

-- relationships
create table register (
    regtime date,
    grade int,
    snum int not null,
    number int not null,
    primary key (snum, number),
    foreign key (snum) references students (snum),
    foreign key (number) references courses (number)
);

create table offers (
    number int not null,
    code int not null,
    primary key (number),
    foreign key (number) references courses (number),
    foreign key (code) references department (code)
);

create table administer (
    code int not null,
    name varchar(50) not null,
    foreign key (code) references department (code),
    foreign key (name) references degrees (name)
);

create table minor (
    snum int not null,
    name varchar(50) not null,
    foreign key (snum) references students (snum),
    foreign key (name) references degrees (name)
);

create table major (
    snum int not null,
    name varchar(50) not null,
    foreign key (snum) references students (snum),
    foreign key (name) references degrees (name)
);

-- NOTE: uncomment the following for a quick light test
-- insert sample departments
-- insert into department (name, code, phone, college)
-- values ('science', 101, 1234567890, 'engineering'),
--        ('arts', 102, 987654321, 'humanities');

-- -- insert sample students
-- insert into students (p_phone, p_addr, c_phone, c_addr, dob, gender, name, snum, ssn)
-- values (1112223333, '123 main st', 4445556666, '456 campus rd', '2000-01-01', 'm', 'john', 1, 111223333),
--        (2223334444, '789 elm st', 5556667777, '101 college ave', '2001-02-02', 'f', 'alice', 2, 222334444);

-- -- insert sample courses
-- insert into courses (name, number, description, credit_hours, level)
-- values ('calc', 101, 'calculus course', 3, 100),
--        ('lit', 102, 'literature course', 3, 200);

-- -- insert sample degrees
-- insert into degrees (name, level)
-- values ('bscs', 1),
--        ('baeng', 2);

-- -- insert registrations (relationship 1)
-- insert into register (regtime, grade, snum, number)
-- values ('2025-01-10', 85, 1, 101),
--        ('2025-01-15', 90, 2, 102);

-- -- insert course offerings (relationship 2)
-- insert into offers (number, code)
-- values (101, 101),
--        (102, 102);

-- -- insert degree administration (relationship 3)
-- insert into administer (code, name)
-- values (101, 'bscs'),
--        (102, 'baeng');

-- -- insert minor records (relationship 4)
-- insert into minor (snum, name)
-- values (2, 'bscs');

-- -- insert major records (relationship 5)
-- insert into major (snum, name)
-- values (1, 'bscs'),
--        (2, 'baeng');

-- --------------------------------------------------
-- -- quick queries to test the data
-- --------------------------------------------------
-- select * from department;
-- select * from students;
-- select * from courses;
-- select * from degrees;
-- select * from register;
-- select * from offers;
-- select * from administer;
-- select * from minor;
-- select * from major;