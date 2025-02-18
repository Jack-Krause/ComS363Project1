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