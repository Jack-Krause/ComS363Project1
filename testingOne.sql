-- create database University;
use university;

drop table if exists register;
drop table if exists offers;
drop table if exists administer;
drop table if exists minor;
drop table if exists major;
drop table if exists courses;
drop table if exists degrees;
drop table if exists students;
drop table if exists departments;


create table departments (
    dname varchar(10) not null,
    dcode int not null,
    phone int,
    college varchar(20),
    primary key (dcode),
    unique (dname)
);

create table students (
    p_phone bigint,
    p_addr varchar(50),
    c_phone bigint,
    c_addr varchar(50),
    dob date,
    gender char(1),
    name varchar(20),
    sid int not null,      -- was snum; renamed to sid
    ssn int,
    primary key (sid)
);

create table courses (
    cname varchar(20),
    cnumber int not null,   -- was number; renamed to cnumber
    description varchar(50),
    credithours int,        -- was credit_hours; renamed to credithours
    level int,
    primary key (cnumber)
);

create table degrees (
    dgname varchar(50) not null,
    level int,
    primary key (dgname)
);

-- relationships
create table register (
    regtime date,
    grade int,
    sid int not null,            -- was snum; renamed to sid
    course_number int not null,  -- was number; renamed to course_number
    primary key (sid, course_number),
    foreign key (sid) references students (sid),
    foreign key (course_number) references courses (cnumber)
);

create table offers (
    cnumber int not null,        -- was number; renamed to cnumber to match courses table
    dcode int not null,
    primary key (cnumber),
    foreign key (cnumber) references courses (cnumber),
    foreign key (dcode) references departments (dcode)
);

create table administer (
    dcode int not null,
    dgname varchar(50) not null,
    foreign key (dcode) references departments (dcode),
    foreign key (dgname) references degrees (dgname)
);

create table minor (
    sid int not null,        -- was snum; renamed to sid
    name varchar(50) not null,  -- was dgname; renamed to name
    foreign key (sid) references students (sid),
    foreign key (name) references degrees (dgname)
);

create table major (
    sid int not null,        -- was snum; renamed to sid
    name varchar(50) not null,  -- was dgname; renamed to name
    foreign key (sid) references students (sid),
    foreign key (name) references degrees (dgname)
);

-- NOTE: uncomment the following for a quick light test
-- insert sample departments
insert into departments (dname, dcode, phone, college)
values ('science', 101, 1234567890, 'engineering'),
       ('arts', 102, 987654321, 'humanities');

-- insert sample students
insert into students (p_phone, p_addr, c_phone, c_addr, dob, gender, name, sid, ssn)
values (1112223333, '123 main st', 4445556666, '456 campus rd', '2000-01-01', 'm', 'john', 1, 111223333),
       (2223334444, '789 elm st', 5556667777, '101 college ave', '2001-02-02', 'f', 'alice', 2, 222334444);

-- insert sample courses
insert into courses (cname, cnumber, description, credithours, level)
values ('calc', 101, 'calculus course', 3, 100),
       ('lit', 102, 'literature course', 3, 200);

-- insert sample degrees
insert into degrees (dgname, level)
values ('bscs', 1),
       ('baeng', 2);

-- insert registrations (relationship 1)
insert into register (regtime, grade, sid, course_number)
values ('2025-01-10', 85, 1, 101),
       ('2025-01-15', 90, 2, 102);

-- insert course offerings (relationship 2)
insert into offers (cnumber, dcode)
values (101, 101),
       (102, 102);

-- insert degree administration (relationship 3)
insert into administer (dcode, dgname)
values (101, 'bscs'),
       (102, 'baeng');

-- insert minor records (relationship 4)
insert into minor (sid, name)
values (2, 'bscs');

-- insert major records (relationship 5)
insert into major (sid, name)
values (1, 'bscs'),
       (2, 'baeng');

--------------------------------------------------
-- quick queries to test the data
--------------------------------------------------
select * from departments;
select * from students;
select * from courses;
select * from degrees;
select * from register;
select * from offers;
select * from administer;
select * from minor;
select * from major;
