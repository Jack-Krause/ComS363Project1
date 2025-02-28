-- create database University;
use university;

drop table if exists register;
drop table if exists offers;
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

