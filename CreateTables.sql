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
    dname varchar(50) not null,
    dcode integer not null,
    phone varchar(10),
    college varchar(20),
    primary key (dcode),
    unique (dname)
);

create table students (
    p_phone varchar(20),
    p_addr varchar(20),
    c_phone varchar(20),
    c_addr varchar(20),
    dob varchar(10),
    gender varchar(1),
    name varchar(20),
    sid integer not null,      -- candidate key
    ssn integer not null,      -- primary key
    primary key (ssn),
    unique (sid)
);

create table courses (
    cname varchar(50),
    cnumber integer not null,
    description varchar(50),
    credithours integer,
    level varchar(20),
    department_code integer,
    primary key (cnumber),
    foreign key (department_code) references departments(dcode)
);

create table degrees (
    dgname varchar(50) not null,
    level varchar(5) not null,
    department_code integer,
    primary key (dgname, level),
    foreign key (department_code) references departments(dcode)
);

create table register (
    sid integer not null,
    course_number integer not null,
    regtime varchar(20),
    grade integer,
    primary key (sid, course_number),
    foreign key (sid) references students(sid),
    foreign key (course_number) references courses(cnumber)
);

create table offers (
    cnumber integer not null,        -- renamed to match courses table
    dcode integer not null,
    primary key (cnumber),
    foreign key (cnumber) references courses(cnumber),
    foreign key (dcode) references departments(dcode)
);

create table administer (
    dcode integer not null,
    dgname varchar(50) not null,
    foreign key (dcode) references departments(dcode),
    foreign key (dgname) references degrees(dgname)
);

create table minor (
    sid integer not null,
    name varchar(50) not null,
    level varchar(5) not null,
    primary key (sid, name, level),
    foreign key (sid) references students(sid),
    foreign key (name, level) references degrees(dgname, level)
);

create table major (
    sid integer not null,
    name varchar(50) not null,
    level varchar(5) not null,
    primary key (sid, name, level),
    foreign key (sid) references students(sid),
    foreign key (name, level) references degrees(dgname, level)
);

-- NOTE: uncomment the following for a quick light test
-- insert sample departments
insert into departments (dname, dcode, phone, college)
values ('science', 101, '1234567890', 'engineering'),
       ('arts', 102, '987654321', 'humanities');

-- insert sample students
insert into students (p_phone, p_addr, c_phone, c_addr, dob, gender, name, sid, ssn)
values ('1112223333', '123 main st', '4445556666', '456 campus rd', '2000-01-01', 'm', 'john', 1, 111223333),
       ('2223334444', '789 elm st', '5556667777', '101 college ave', '2001-02-02', 'f', 'alice', 2, 222334444);

-- insert sample courses
insert into courses (cname, cnumber, description, credithours, level)
values ('calc', 101, 'calculus course', 3, '100'),
       ('lit', 102, 'literature course', 3, '200');

-- insert sample degrees
insert into degrees (dgname, level)
values ('bscs', '1'),
       ('baeng', '2');

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
insert into minor (sid, name, level)
values (2, 'bscs', '1');

-- insert major records (relationship 5)
insert into major (sid, name, level)
values (1, 'bscs', '1'),
       (2, 'baeng', '2');

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
select * from students;
