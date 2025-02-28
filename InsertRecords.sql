SET GLOBAL local_infile=1;


load data local infile 'C:\\Users\\jackm\\OneDrive\\CS3630\\Project1\\students.csv'
into table students
fields terminated by ','
enclosed by '"' 
lines terminated by '\r\n'
ignore 1 rows
(sid, ssn, name, gender, dob, c_addr, c_phone, p_addr, p_phone);
-- (sid, ssn, @col3, @col4, @col5, @col6, @col7, @col8, @col9);


load data local infile 'C:\\Users\\jackm\\OneDrive\\CS3630\\Project1\\departments.csv'
into table departments
fields terminated by ','
enclosed by '"'
lines terminated by '\r\n'
ignore 1 rows
(dcode, dname, phone, college);


load data local infile 'C:\\Users\\jackm\\OneDrive\\CS3630\\Project1\\courses.csv'
into table courses
fields terminated by ','
enclosed by '"'
lines terminated by '\r\n'
ignore 1 rows
(cnumber, cname, description, credithours, level, department_code);


load data local infile 'C:\\Users\\jackm\\OneDrive\\CS3630\\Project1\\degrees.csv'
into table degrees
fields terminated by ','
enclosed by '"'
lines terminated by '\r\n'
ignore 1 rows
(dgname, level, department_code);


load data local infile 'C:\\Users\\jackm\\OneDrive\\CS3630\\Project1\\major.csv'
into table major
fields terminated by ','
enclosed by '"'
lines terminated by '\r\n'
ignore 1 rows
(sid, name, level);


load data local infile 'C:\\Users\\jackm\\OneDrive\\CS3630\\Project1\\minor.csv'
into table minor
fields terminated by ','
enclosed by '"'
lines terminated by '\r\n'
ignore 1 rows
(sid, name, level);


load data local infile 'C:\\Users\\jackm\\OneDrive\\CS3630\\Project1\\register.csv'
into table register
fields terminated by ','
enclosed by '"'
lines terminated by '\r\n'
ignore 1 rows
(sid, course_number, regtime, grade);


select * from departments;
select * from students;
select * from courses;
select * from major;
select * from minor;
select * from register;
select count(*) from courses;

select name 
from students s
join register r on r.sid = s.sid
join register r on r.course_number = c.course



