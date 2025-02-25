SET GLOBAL local_infile=1;

create table students (
    sid int,
    ssn int,
    primary key (ssn)
);

load data local infile 'C:\\Users\\jackm\\OneDrive\\CS3630\\Project1\\students.csv'
into table students
fields terminated by ','
enclosed by '"' 
lines terminated by '\r\n'
ignore 1 rows
(sid, ssn, @col3, @col4, @col5, @col6, @col7, @col8, @col9);
