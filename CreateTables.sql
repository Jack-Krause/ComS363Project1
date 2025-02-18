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
    foreign key (code) references department (code),
    foreign key (name) references degrees
);

create table minor (
    snum int,
    name varchar(50),
    foreign key (snum) references students (snum),
    foreign key (name) references degrees (name)
);

create table major (
    snum int,
    name varchar(50),
    foreign key (snum) references students (snum),
    foreign key (name) references degrees (name)
);
