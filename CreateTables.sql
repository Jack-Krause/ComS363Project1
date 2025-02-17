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

